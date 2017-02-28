using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestExercises
{
    public class MonthDay
    {
        //List<KeyValuePair<int, int>> monthList = new List<KeyValuePair<int, int>>();
        private int[] monthList = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        public MonthDay()
        {
        }

        public int GetNumDaysinMonth(int month, int year)
        {
            if (isInputValid(month, year))
            {
                // Year/month is valid, checking for leap years.
                if (month == 2)
                {
                    if (isLeapYear(year))
                    {
                        return monthList[month - 1] + 1;
                    }
                    else
                    {
                        return monthList[month - 1];
                    }
                }
                else
                {
                    return monthList[month - 1];
                }
            }
            else
            {
                // Input was invalid.
                return 0;
            }
        }

        private Boolean isInputValid(int month, int year)
        {
            if (month >= 1 && month <= 12 && year >= 0 && year <= Int32.MaxValue)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        private Boolean isLeapYear(int year)
        {
            if (year % 400 == 0)
            {
                return true;
            }
            else if (year % 100 == 0)
            {
                return false;
            }
            else if (year % 4 == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
