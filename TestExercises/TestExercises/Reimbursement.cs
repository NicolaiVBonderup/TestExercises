using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestExercises
{
    public class Reimbursement
    {

        public Reimbursement()
        {
            
        }

        public int CalculateDeductible(bool deductible, string typeOfVisit) 
        {
            if (deductible)
            {
                if (typeOfVisit == "D")
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
            else
            {
                // Deductible not met, rejected.
                return 0;
            }
        }
    }
}
