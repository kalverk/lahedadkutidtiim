package com.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SpordiKaartTests {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(CategoryButtonSATest.class);
    suite.addTestSuite(LoginViaFbSATest.class);
    suite.addTestSuite(CategoryQuerySATest.class);
    suite.addTestSuite(RatingCommentSATest.class);
    suite.addTestSuite(AddLocationSATest.class);
    suite.addTestSuite(LogOutSATest.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
