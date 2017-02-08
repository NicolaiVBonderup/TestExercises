# TestExercise1TriangleCalc

Simple triangle calculator for Test class, Software Development course at CPHBusiness.

Possible test cases below.

| Test Case                                   | Input         | Output      |
| ------------------------------------------- |:-------------:| -----------:|
| Test for equilateral                        | (5,5,5)       | Equilateral |
| Test for isosceles, first two integers      | (4,4,3)       | Isosceles   |
| Test for isosceles, last two integers       | (3,4,4)       | Isosceles   |
| Test for isosceles, first and last integers | (4,3,4)       | Isosceles   |
| Test for scalene                            | (4,3,2)       | Scalene     |
| Test for 0 input                            | (0,4,4)       | Error       |
| Test for string input                       | (4,4,'Hello') | Error       |
| Test for string and integer input           | (4,4,'Hello4')| Error       |
| Test for symbol input                       | (4,4,'&')     | Error       |
| Test for decimal input                      | (4,4,4.5)     | Error       |
| Test for empty input                        | (4,4,'')      | Error       |
| Test for incorrect triangle                 | (4,4,10)      | Error       |
