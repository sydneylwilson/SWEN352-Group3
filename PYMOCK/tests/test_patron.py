import unittest;
from unittest.mock import Mock
from library import patron
import library

class TestPatron(unittest.TestCase):

    def setUp(self):
        self.pat = patron.Patron('fname', 'lname', '20', '1234')

    def test_equals(self):
        pat_twin = patron.Patron('fname', 'lname', '20', '1234')
        self.assertTrue(self.pat.__eq__(pat_twin));
    
    def test_not_equals(self):
        pat_twin = patron.Patron('fname', 'lname', '20', '1234')
        self.assertFalse(self.pat.__ne__(pat_twin));

    def test_valid_name(self):
        self.assertTrue(isinstance(self.pat, patron.Patron))

    def test_invalid_name(self):
        self.assertRaises(patron.InvalidNameException, patron.Patron, '1fname', '1lname', '20', '1234')

    def test_add_borrowed_books(self):
        self.pat.add_borrowed_book("Book");
        books = self.pat.get_borrowed_books();
        self.assertEqual(len(books), 1);
    
    def test_add_borrowed_books_duplicate(self):
        self.pat.add_borrowed_book("Book");
        self.pat.add_borrowed_book("Book");
        books = self.pat.get_borrowed_books();
        self.assertEqual(len(books), 1)

    def test_get_borrowed_books(self):
        self.pat.add_borrowed_book("Book");
        books = self.pat.get_borrowed_books();
        self.assertTrue(books[0], "book");

    def test_return_borrowed_books(self):
        self.pat.add_borrowed_book("Book");
        self.pat.return_borrowed_book("Book");
        books = self.pat.get_borrowed_books();
        self.assertEqual(len(books), 0);
    
    def test_return_borrowed_books_not_available(self):
        self.pat.add_borrowed_book("Book");
        self.pat.return_borrowed_book("Not available book");
        books = self.pat.get_borrowed_books();
        self.assertEqual(len(books), 1);

    def test_get_fname(self):
        name = self.pat.get_fname();
        self.assertEqual(name, 'fname');

    def test_get_lname(self):
        name = self.pat.get_lname();
        self.assertEqual(name, 'lname');

    def test_get_age(self):
        name = self.pat.get_age();
        self.assertEqual(name, '20');

    def test_get_memberID(self):
        name = self.pat.get_memberID();
        self.assertEqual(name, '1234');
    
    #########################################################################
    ############################## MUTMUT ###################################
    #########################################################################
    
    def test_init_bounds( self ):
        """
        Mutant 117
-        if re.search('\d', fname) or re.search('\d', lname):
+        if re.search('XX\dXX', fname) or re.search('\d', lname):
             raise InvalidNameException("Name should not contain numbers")
         self.fname = fname
         self.lname = lname
        """
        with self.assertRaises(patron.InvalidNameException):
            paul = Mock(return_value=patron.Patron('1234', None, '20', '1234'))
            paul2 = Mock(return_value=patron.Patron(None, '1234', '20', '1234'))
            paul4 = Mock(return_value=patron.Patron('Sam', '1234', '20', '1234'))
            paul5 = Mock(return_value=patron.Patron('Sam', None, '20', '1234'))
            
    def test_init_bounds2( self ):
        """
        Mutant 118
        """
        with self.assertRaises(patron.InvalidNameException):
            paul4 = Mock(return_value=patron.Patron('Sam', '1234', '20', '1234'))
            
    def test_init_bounds3( self ):
        """
        Mutant 120
        if re.search('\d', fname) or re.search('\d', lname):
-            raise InvalidNameException("Name should not contain numbers")
+            raise InvalidNameException("XXName should not contain numbersXX")
         self.fname = fname
         self.lname = lname
         self.age = age
        """
        with self.assertRaises(patron.InvalidNameException) as E:
            paul = Mock(return_value=patron.Patron('héllo 123', 'Dude', '20', '1234'))
            raise patron.InvalidNameException("XXName should not contain numbersXX")
        self.assertNotEqual(str(E.exception), "XXName should not contain numbersXX")
        # have to break the re.search to get this to work without getting a InvalidNameException somehow