using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using TestExercises;

namespace TestExercises.Test
{
    [TestFixture]
    public class TestClassEquivalencesMonth
    {
        private MonthDay md = new MonthDay();

        [TestCase]
        public void TestMonthLowerBoundary()
        {
            int result = md.GetNumDaysinMonth(-1, 1990);
            Assert.AreEqual(0, result);
        }

        [TestCase]
        public void TestMonthUpperBoundary()
        {
            int result = md.GetNumDaysinMonth(13, 1990);
            Assert.AreEqual(0, result);
        }

        [TestCase]
        public void TestMonthFebruaryNonLeap()
        {
            int result = md.GetNumDaysinMonth(2, 1900);
            Assert.AreEqual(28, result);
        }

        [TestCase]
        public void TestMonthFebruaryWithLeap()
        {
            int result = md.GetNumDaysinMonth(2, 2000);
            Assert.AreEqual(29, result);
        }

        [TestCase]
        public void TestMonth31()
        {
            // The months Jan, Mar, May, Jul, Aug, Oct and Dec.
            int[] thirtyOneDayMonths = {1, 3, 5, 7, 8, 10, 12};
            foreach (var month in thirtyOneDayMonths)
            {
                int result = md.GetNumDaysinMonth(month, 2017);
                Assert.AreEqual(31, result);
            }
        }

        [TestCase]
        public void TestMonth30()
        {
            // The months Apr, Jun, Sep and Nov.
            int[] thirtyDayMonths = {4, 6, 9, 11};
            foreach (var month in thirtyDayMonths)
            {
                int result = md.GetNumDaysinMonth(month, 2017);
                Assert.AreEqual(30, result);
            }
        }
    }

    [TestFixture]
    public class TestClassEquivalencesYear
    {
        private MonthDay md = new MonthDay();

        [TestCase]
        public void TestLeapYear()
        {
            int result = md.GetNumDaysinMonth(2, 400);
            Assert.AreEqual(29, result);
        }

        [TestCase]
        public void TestNonLeapYear()
        {
            int result = md.GetNumDaysinMonth(2, 100);
            Assert.AreEqual(28, result);
        }

        [TestCase]
        public void TestYearLowerBoundary()
        {
            int result = md.GetNumDaysinMonth(5, -1990);
            Assert.AreEqual(0, result);
        }

        [TestCase]
        public void TestYearUpperBoundary()
        {
            int result = md.GetNumDaysinMonth(-1, unchecked(Int32.MaxValue + 1));
            Assert.AreEqual(0, result);
        }
    }

    [TestFixture]
    public class TestClassEquivalncesMonthYear
    {
        private MonthDay md = new MonthDay();

        [TestCase]
        public void thirtyOneDayMonthNonLeap()
        {
            int result = md.GetNumDaysinMonth(1, 1999);
            Assert.AreEqual(31, result);
        }

        [TestCase]
        public void thirtyOneDayMonthLeap()
        {
            int result = md.GetNumDaysinMonth(1, 2000);
            Assert.AreEqual(31, result);
        }

        [TestCase]
        public void thirtyDayMonthNonLeap()
        {
            int result = md.GetNumDaysinMonth(4, 1999);
            Assert.AreEqual(30, result);
        }

        [TestCase]
        public void thirtyDayMonthLeap()
        {
            int result = md.GetNumDaysinMonth(4, 2000);
            Assert.AreEqual(30, result);
        }

        [TestCase]
        public void februaryNonLeap()
        {
            int result = md.GetNumDaysinMonth(2, 1999);
            Assert.AreEqual(28, result);
        }

        [TestCase]
        public void februaryLeap()
        {
            int result = md.GetNumDaysinMonth(2, 2000);
            Assert.AreEqual(29, result);
        }
    }

    [TestFixture]
    public class TestClassBoundaryValues
    {
        private MonthDay md = new MonthDay();

        [TestCase]
        public void testLeapYearsDivBy400()
        {
            int leapYear = 400;
            for (int i = 0; i < 10; i++)
            {
                int result = md.GetNumDaysinMonth(2, leapYear);
                Assert.AreEqual(29, result);
                leapYear = leapYear + 400;
            }
        }

        [TestCase]
        public void testLeapYearsDivBy100()
        {
            int leapYear = 100;
            for (int i = 0; i < 10; i++)
            {
                int result = md.GetNumDaysinMonth(2, leapYear);
                Assert.AreEqual(28, result);
                if ((leapYear + 100) % 400 == 0)
                {
                    leapYear = leapYear + 200;
                }
                else
                {
                    leapYear = leapYear + 100;
                }
            }
        }

        [TestCase]
        public void testNonPositiveMonthBoundary()
        {
            int result = md.GetNumDaysinMonth(0, 1999);
            Assert.AreEqual(0, result);
        }

        [TestCase]
        public void testPositiveMonthBoundary()
        {
            int result = md.GetNumDaysinMonth(13, 1999);
            Assert.AreEqual(0, result);
        }
    }
}