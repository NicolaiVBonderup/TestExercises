using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualBasic;

namespace Exercise1TriangleCalc
{
    class Program
    {
        static void Main(string[] args)
        {
            string xStr = Interaction.InputBox("Input length of side x", "Input Number", "", -1, -1);
            string yStr = Interaction.InputBox("Input length of side y", "Input Number", "", -1, -1);
            string zStr = Interaction.InputBox("Input length of side z", "Input Number", "", -1, -1);

            int x = checkForAttributeTyping(xStr);
            int y = checkForAttributeTyping(yStr);
            int z = checkForAttributeTyping(zStr);

            calculateTriangle(x, y, z);

        }


        static int checkForAttributeTyping(string str)
        {
            int convertedStr = 0;

            try
            {
                convertedStr = Int32.Parse(str);
            }
            catch
            {
                string errorMsg = "The value '" + str + "' is not an integer.";
                System.Windows.Forms.MessageBox.Show(errorMsg);
                System.Environment.Exit(1);
            }

            return convertedStr;
        }

        static void calculateTriangle(int x, int y, int z)
        {

            if (x == 0 || y == 0 || z == 0)
            {
                System.Windows.Forms.MessageBox.Show("A side can't have a length of 0. That's the absence of a side. C'mon now.");
            }
            else if (x == y && x == z)
            {
                System.Windows.Forms.MessageBox.Show("The triangle is equilateral.");
            }
            else if (x == y || y == z || z == x)
            {
                System.Windows.Forms.MessageBox.Show("The triangle is isosceles.");
            }
            else
            {
                System.Windows.Forms.MessageBox.Show("The triangle is scalene.");
            }

        }


    }
}