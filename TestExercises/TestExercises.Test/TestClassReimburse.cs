using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework.Internal;
using NUnit.Framework;

namespace TestExercises.Test
{
    [TestFixture]
    public class TestClassReimburseDeductible
    {
        private Reimbursement re = new Reimbursement();

        [TestCase]
        public void ReimburseForDoctorVisit()
        {
            int result = re.CalculateDeductible(true, "D");
            Assert.AreEqual(1,result);
        }

        [TestCase]
        public void ReimburseForHospitalVisit()
        {
            int result = re.CalculateDeductible(true, "H");
            Assert.AreEqual(2, result);
        }

        [TestCase]
        public void DontReimburseForDoctorVisit()
        {
            int result = re.CalculateDeductible(false, "D");
            Assert.AreEqual(0, result);
        }

        [TestCase]
        public void DontReimburseForHospitalVisit()
        {
            int result = re.CalculateDeductible(false, "H");
            Assert.AreEqual(0, result);
        }

    }

}
