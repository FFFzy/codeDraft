import unittest
from teacher import *

class Test_teacher(unittest.TestCase):
    def setUp(self):
        print('test kick on')
        self.obj = Teacher("张三",35,"胡主任")

    def test_gain(self):
        self.assertEqual(15,self.obj.gain(15))
        print('test gain')

    def test_decrease(self):
        self.assertEqual(10,self.obj.decrease(5))
        print('test decrease')

    def tearDown(self):
        print('test is over')

if __name__=='__main__':
    unittest.main()
