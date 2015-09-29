package tamseo.utils;

public class Runner {

  private static final String CORE_EXAMPLES_JAVA_DIR =  "src/main/java/";
  private static final String CORE_EXAMPLES_JS_DIR =   "src/main/js/";
  private static final String CORE_EXAMPLES_GROOVY_DIR =  "src/main/groovy/";

  public static void runClusteredExample(Class clazz) {
    ExampleRunner.runJavaExample(CORE_EXAMPLES_JAVA_DIR, clazz, true);
  }

  public static void runExample(Class clazz) {
    ExampleRunner.runJavaExample(CORE_EXAMPLES_JAVA_DIR, clazz, false);
  }

  // JavaScript examples

  public static void runJSExample(String scriptName) {
    ExampleRunner.runJSExample(CORE_EXAMPLES_JS_DIR, scriptName, false);
  }

  public static void runGroovyExampleClustered(String scriptName) {
    ExampleRunner.runJSExample(CORE_EXAMPLES_GROOVY_DIR, scriptName, true);
  }

}
