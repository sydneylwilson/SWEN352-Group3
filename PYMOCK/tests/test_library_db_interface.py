import unittest
from library.library_db_interface import *
# from tests_data import *
from unittest.mock import Mock, patch, call

class TestLibraryDBInterface(unittest.TestCase):
    def setUp(self):
        self.db_interface = Library_DB()
    
    def test_insert_patron(self):
        patron_mock = Mock()
        self.db_interface.retrieve_patron = Mock(return_value=None)
        data = {'fname':'Jane', 'lname':'Doe', 'age':'21', 'memberID':'1', 'borrowed_books':[]}
        self.db_interface.convert_patron_to_db_format = Mock(return_value=data)
        self.db_interface.db.insert = Mock(side_effect=lambda x: 10 if x==data else 0)
        self.assertEqual(self.db_interface.insert_patron(patron_mock), 10)

    def test_insert_patron_parameters_none(self):
        self.db_interface.convert_patron_to_db_format = Mock(return_value=None)
        self.assertIsNone(self.db_interface.insert_patron(None))

    def test_insert_existing_patron(self):
        patron_mock = Patron('Jane', 'Doe', '21', [])
        self.db_interface.retrieve_patron = Mock(return_value=patron_mock)
        result = self.db_interface.insert_patron(patron_mock)
        self.assertIsNone(result)
    
    def test_get_patron_count(self):
        self.assertEqual(self.db_interface.get_patron_count(), 3)
    
    def test_get_all_patrons(self):
        self.assertEqual(len(self.db_interface.get_all_patrons()), 3)
    
    def test_update_patron(self):
        data = {'fname':'Jeff', 'lname':'Smith', 'age':'25', 'memberID':'2', 'borrowed_books':[]}
        self.db_interface.convert_patron_to_db_format = Mock(return_value=data)
        db_update_mock = Mock()
        self.db_interface.db.update = db_update_mock
        self.db_interface.update_patron(Mock())
        db_update_mock.assert_called()

    def test_update_patron_none(self):
        self.db_interface.convert_patron_to_db_format = Mock()
        result = self.db_interface.update_patron(None)
        self.assertIsNone(result)
    
    def test_retrieve_patron(self):
        retrieve_patron_mock = Mock()
        data = {'fname': 'Jeff', 'lname': 'Smith', 'age': '25', 'memberID': '2', 'borrowed_books': []}
        self.db_interface.retrieve_patron = Mock(return_value=data)
        self.assertEqual(self.db_interface.retrieve_patron(retrieve_patron_mock), data)

    def test_retrieve_patron_memberID_dne(self):
        self.db_interface.db.search = Mock(return_value=[])
        memberID_to_retrieve = '12345'
        result = self.db_interface.retrieve_patron(memberID_to_retrieve)
        self.assertIsNone(result)

    def test_close_db(self):
        self.assertEqual(self.db_interface.close_db(), None)
    
    def test_convert_patron_to_db_format(self):
        patron_mock = Mock()
        patron_mock.get_fname = Mock(return_value=1)
        patron_mock.get_lname = Mock(return_value=2)
        patron_mock.get_age = Mock(return_value=3)
        patron_mock.get_memberID = Mock(return_value=4)
        patron_mock.get_borrowed_books = Mock(return_value=5)
        self.assertEqual(self.db_interface.convert_patron_to_db_format(patron_mock),{'fname': 1, 'lname': 2, 'age': 3, 'memberID': 4,'borrowed_books': 5})
    
    #########################################################################
    ############################## MUTMUT ###################################
    #########################################################################

    def test_update_patron_bounds( self ):
        """
        Mutant 100
        """
        invalid_patron = Mock(spec=Patron)
        invalid_patron.get_memberID.return_value = None
        result = self.db_interface.update_patron(invalid_patron)
        self.assertIsNone(result)
    
    def test_retrieve_patron_bounds( self ):
        """
        Mutant 102
        """
        memberID_to_retrieve = None
        result = self.db_interface.retrieve_patron(memberID_to_retrieve)
        self.assertIsNone(result)
    
    @patch.object(Library_DB, 'convert_patron_to_db_format', return_value=None)
    def test_update_patron_with_data_none(self, mock_convert_patron_to_db_format):
        """
        Mutant 99
        I hated this one
            Ivan Rojas
        """
        # Test the update_patron() method with data = None
        patron = Patron('John', 'Doe', 30, '123')
        result = self.db_interface.update_patron(patron)
        self.assertIsNone(result)
        mock_convert_patron_to_db_format.assert_called_once_with(patron)     
    
    @patch.object(Library_DB, 'retrieve_patron', return_value=[{'fname': 'John', 'lname': 'Doe', 'age': 30, 'memberID': '12345'}, {'fname': 'Jane', 'lname': 'Doe', 'age': 25, 'memberID': '67890'}])
    def test_retrieve_patron_with_mutant_106(self, mock_retrieve_patron):
        # Test the retrieve_patron() method with Mutant 106
        memberID = '12345'
        result = self.db_interface.retrieve_patron(memberID)
        self.assertIsInstance(result, list)
        self.assertEqual(result[0].get('fname'), 'John')
        self.assertEqual(result[0].get('lname'), 'Doe')
        self.assertEqual(result[0].get('age'), 30)
        self.assertEqual(result[0].get('memberID'), '12345')

    