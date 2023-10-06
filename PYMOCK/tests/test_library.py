"""
Filename: test_library.py
Testing for the Library class used for SWEN-352 mocking activity.
"""

import unittest
from unittest.mock import Mock
from library import library
from library import patron
import json

class TestLibrary( unittest.TestCase ):
    
    def setUp(self):
        self.lib = library.Library()
        self.mock_db = library.Library_DB()
        self.lib.db = self.mock_db
        # self.books_data = [{'title': 'Learning Python', 'ebook_count': 3}, {'title': 'Learning Python (Learning)', 'ebook_count': 1}, {'title': 'Learning Python', 'ebook_count': 1}, {'title': 'Learn to Program Using Python', 'ebook_count': 1}, {'title': 'Aprendendo Python', 'ebook_count': 1}, {'title': 'Python Basics', 'ebook_count': 1}]
        with open('tests_data/ebooks.txt', 'r') as f:
            self.books_data = json.loads(f.read())
        
    
    def test_is_ebook_true(self):
        """
        Test the is_ebook method but getting true.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertTrue(self.lib.is_ebook('learning python'))
        
    def test_is_ebook_false(self):
        """
        Test the is_ebook method but attempting fail.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertFalse(self.lib.is_ebook('learning Java'))
    
    def test_get_ebooks_count_true(self):
        """
        Test the get_ebooks_count method and getting a correct count.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertEqual(self.lib.get_ebooks_count('learning python'), 8)
        
    def test_get_ebooks_count_false(self):
        """
        Test the get_ebooks_count method and getting a incorrect count.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertNotEqual(self.lib.get_ebooks_count('learning python'), 5)
    
    
    def test_is_book_by_author_true(self):
        """
        Test method for is_book_by_author and getting a true.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertTrue(self.lib.is_book_by_author('Mark Lutz', 'learning python'))
        
    def test_is_book_by_author_false(self):
        """
        Test method for is_book_by_author and getting a false.
        """
        self.lib.api.get_ebooks = Mock(return_value=self.books_data)
        self.assertFalse(self.lib.is_book_by_author('Paul Rudd', 'learning Java'))
        
    def test_languages_for_book_false(self):
        """
        Test method for get_languages_for_book and getting a incorrect set.
        """
        self.lib.api.get_book_info = Mock(return_value=self.books_data)
        self.assertNotEqual(self.lib.get_languages_for_book('learning python'), {'eng', 'spa'})
    
    def test_languages_for_book_true(self):
        """
        Test method for get_languages_for_book and getting a correct set.
        """
        self.lib.api.get_book_info = Mock(return_value=self.books_data)
        self.assertIsInstance(self.lib.get_languages_for_book('learning python'), set)

    def test_register_patron( self ):
        """
        Test method for register_patron and getting a correct set.
        """
        self.lib.db.insert_patron = Mock(return_value=True)
        self.assertTrue(self.lib.register_patron('John', 'Doe', 21, 123456))
    
    def test_register_patron_false( self ):
        """
        Test method for register_patron and getting a incorrect set.
        """
        self.lib.db.insert_patron = Mock(return_value=False)
        self.assertFalse(self.lib.register_patron('John', 'Doe', 21, 123456))
        
    def test_is_patron_registerd( self ):
        """
        Test method for is_patron_registerd and getting a correct set.
        """
        patron = Mock()
        self.lib.db.retrieve_patron = Mock(return_value=123)
        self.assertTrue(self.lib.is_patron_registered(patron))
        
    def test_is_patron_registerd_false( self ):
        """
        Test method for is_patron_registerd and getting a incorrect set.
        """
        patron = Mock()
        self.lib.db.retrieve_patron = Mock(return_value=None)
        self.assertFalse(self.lib.is_patron_registered(patron))
        
    def test_borrow_book__fail( self ):
        """
        Test method for borrow_book and getting a incorrect set aka a None value.
        """
        patron = Mock()
        self.lib.db.retrieve_patron = Mock(return_value=patron)
        self.lib.db.update_patron = Mock(return_value=False)
        self.assertIsNone(self.lib.borrow_book('learning python', patron))
        
    def test_return_book( self ):
        """
        Test method for return_book and getting a correct set.
        """
        patron = Mock()
        self.lib.db.retrieve_patron = Mock(return_value=patron)
        self.lib.db.update_patron = Mock(return_value=True)
        self.assertIsNone(self.lib.return_borrowed_book('learning python', patron))
    
    def test_is_book_borrowed( self ):
        """
        Test method for is_book_borrowed and getting a correct set.
        """
        patron = Mock()
        patron.get_borrowed_books = Mock(return_value=['learning python'])
        self.assertTrue(self.lib.is_book_borrowed('learning python', patron))
    
if __name__ == '__main__':
    unittest.main()