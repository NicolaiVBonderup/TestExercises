using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualBasic;

namespace TestExercises
{
    class Program
    {
        static void Main(string[] args)
        {

            RunMonthDayFunction();
            RunReimbursementFunction();
        }

        static void RunMonthDayFunction()
        {
            int year, month;
            MonthDay md = new MonthDay();
            string yearStr = Interaction.InputBox("Input year", "Input Number", "", -1, -1);
            string monthStr = Interaction.InputBox("Input month", "Input Number", "", -1, -1);

            year = Int32.Parse(yearStr);
            month = Int32.Parse(monthStr);

            int result = md.GetNumDaysinMonth(month, year);

            if (result == 0)
            {
                System.Windows.Forms.MessageBox.Show("Input is invalid.");
            }
            else
            {
                System.Windows.Forms.MessageBox.Show("The number of days in month " + month + " is " + result);
            }
        }

        static void RunReimbursementFunction()
        {
            Reimbursement re = new Reimbursement();
            re.CalculateDeductible(true,"D");

        }
    }
}
