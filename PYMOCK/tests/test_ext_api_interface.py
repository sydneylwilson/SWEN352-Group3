import unittest
from library import ext_api_interface
from unittest.mock import Mock
import requests
import json

class TestExtApiInterface(unittest.TestCase):
    def setUp(self):
        self.api = ext_api_interface.Books_API()
        self.book = "learning python"
        with open('tests_data/ebooks.txt', 'r') as f:
            self.books_data = json.loads(f.read())
        with open('tests_data/json_data.txt', 'r') as f:
            self.json_data = json.loads(f.read())

    def test_make_request_True(self):
        attr = {'json.return_value': dict()}
        requests.get = Mock(return_value = Mock(status_code = 200, **attr))
        self.assertEqual(self.api.make_request(""), dict())
        
    def test_make_request_false(self):
        requests.get = Mock(return_value = Mock(status_code = 100))
        self.assertEqual(self.api.make_request(""), None)
        
    
    def test_make_request_connection_error(self):
        ext_api_interface.requests.get = Mock(side_effect=requests.ConnectionError)
        url = "some url"
        self.assertEqual(self.api.make_request(url), None)

    def test_is_book_available_True(self):
        """
        Test method for book availability and getting a true.
        """
        self.api.make_request = Mock(return_value=self.json_data)
        self.assertTrue(self.api.is_book_available(self.book))
    
    def test_is_book_available_False(self):
        """
        Test method for book availability and getting a false.
        """
        self.api.make_request = Mock(return_value=None)
        self.assertFalse(self.api.is_book_available(self.book))

    def test_books_by_author_True(self):
        """
        Test method for books by author and getting a true.
        """
        self.api.make_request = Mock(return_value=self.json_data)
        self.assertNotEqual(self.api.books_by_author("Wei-Meng Lee"), self.books_data)

    def test_books_by_author_False(self):
        """
        Test method for books by author and getting a false.
        """
        self.api.make_request = Mock(return_value=None)
        self.assertEqual(self.api.books_by_author("Wei-Meng Lee"), [])

    
    def test_get_book_info_True(self):
        """
        Test method for book information and getting a true.
        """
        self.api.make_request = Mock(return_value=self.json_data)
        self.assertNotEqual(self.api.get_book_info(self.book), self.books_data)

    def test_get_book_info_False(self):
        """
        Test method for book information and getting a false.
        """
        self.api.make_request = Mock(return_value=None)
        self.assertEqual(self.api.get_book_info(self.book), [])
        
    def test_get_ebooks_False(self):
        """
        Test method for ebooks and getting a false.
        """
        self.api.make_request = Mock(return_value=None)
        self.assertEqual(self.api.get_ebooks(self.book), [])
        
    def test_get_ebooks_True(self):
        """
        Test method for ebooks and getting a true.
        """
        self.api.make_request = Mock(return_value=self.json_data)
        self.assertEqual(self.api.get_ebooks(self.book), self.books_data)


    def test_make_request_False(self):
        requests.get = Mock(return_value=Mock(status_code=100))
        self.assertEqual(self.api.make_request(""), None)

    def test_get_ebooks(self):
        self.api.make_request = Mock(return_value=self.json_data)
        self.assertEqual(self.api.get_ebooks(self.book), self.books_data)
    
    #########################################################################
    ############################## MUTMUT ###################################
    #########################################################################

    def test_is_book_available_bounds( self ):
        """
        Test method for book availability and getting a true.
        """
        self.api.make_request = Mock(return_value={'docs': [{'title': 'Learning Python', 'author': 'Mark Lutz', 'publisher': 'O\'Reilly'}]})
        result = self.api.is_book_available('Learning Python')
        self.assertTrue(result)
        
        self.api.make_request = Mock(return_value={'docs': []})
        result = self.api.get_book_info('Non-existent Book')
        self.assertFalse(result)
        
    def test_books_by_author_bounds( self ):
        """
        Ivan Rojas was here
        """
        self.api.make_request = Mock(return_value=None)
        result = self.api.books_by_author('Non-existent Author')
        self.assertEqual(result, [])
    
    def test_get_book_info_bounds( self ):
        """
        Ivan Rojas was here
        """
        self.api.make_request = Mock(return_value=None)
        result = self.api.get_book_info('Non-existent Book')
        self.assertEqual(result, [])
        
        self.api.make_request = Mock(return_value = {'docs': [{'title': 'Learning Python', 'publisher': 'O\'Reilly Media', 'publish_year': '2013', 'language': 'en'}, {'title': 'Python for Data Science Handbook', 'publisher': 'O\'Reilly Media', 'publish_year': '2016', 'language': 'en'}]})
        result = self.api.get_book_info('Python')
        self.assertEqual(result, [{'title': 'Learning Python', 'publisher': 'O\'Reilly Media', 'publish_year': '2013', 'language': 'en'}, {'title': 'Python for Data Science Handbook', 'publisher': 'O\'Reilly Media', 'publish_year': '2016', 'language': 'en'}])
