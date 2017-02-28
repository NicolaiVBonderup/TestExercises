# TestExercises Week 4

## How much of the code is covered by test cases to start out?

Total code coverage is at 67%. Code coverage of MonthDay is 95%, whereas Reimbursement is 100%, and Program/Main is 0%.

## Could and should the code coverage of your project be raised?

As a rule of thumb, code coverage should be around 80%, so sure, it could be raised.

## How?

The code that is not covered is primarily VisualBasic dialog windows, and I do not know how to unit test those. In general most of the Program should be moved into a different class, but this was simple enough that I didn't feel the need. You could (possibly?) test the dialog boxes, and could (and definitely should) test the input from the dialog boxes to ensure that the input is parsable to Int32.
