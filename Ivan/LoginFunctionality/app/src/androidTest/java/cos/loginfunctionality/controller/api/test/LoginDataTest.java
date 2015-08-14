/**
 * File generated by Magnet rest2mobile 1.1 - Jul 2, 2015 9:50:52 PM
 * @see {@link http://developer.magnet.com}
 */

package cos.loginfunctionality.controller.api.test;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Suppress;

import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.exception.SchemaException;
import java.util.concurrent.ExecutionException;

import cos.loginfunctionality.controller.api.LoginData;
import cos.loginfunctionality.controller.api.LoginDataFactory;

/**
* This is generated stub to test {@link LoginData}
* <p>
* All test cases are suppressed by defaullt. To run the test, you need to fix all the FIXMEs first :
* <ul>
* <li>Set proper value for the parameters
* <li>Remove out the @Suppress annotation
* <li>(optional)Add more asserts for the result
* <ul><p>
*/

public class LoginDataTest extends InstrumentationTestCase {
  private LoginData loginData;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    // Instantiate a controller
    MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(this.getInstrumentation().getTargetContext());
    LoginDataFactory controllerFactory = new LoginDataFactory(magnetClient);
    loginData = controllerFactory.obtainInstance();

    assertNotNull(loginData);
  }

  /**
    * Generated unit test for {@link LoginData#getLoginData}
    */
  @Suppress //FIXME : set proper parameter value and un-suppress this test case
  @SmallTest
  public void testGetLoginData() throws SchemaException, ExecutionException, InterruptedException {
    Call<Void> callObject = loginData.getLoginData(null);
    assertNotNull(callObject);

    Void result = callObject.get();
    
    //TODO : add more asserts
  }

}
