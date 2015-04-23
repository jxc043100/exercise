import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Test cases for Rotation.
 */
public class RotationTest extends TestCase {
  AncestorTree tree;
  
  @Override
  public void setUp() {
    tree = new AncestorTree();
  }
  
  public void testRotation_success() {
    assertTrue(Rotation.isRotation(
        Arrays.asList(1, 1, 2, 3, 4), 
        Arrays.asList(1, 2, 3, 4, 1)));
  }
  
  public void testRotation_sameValues() {
    assertTrue(Rotation.isRotation(
        Arrays.asList(1, 1, 1, 1, 1), 
        Arrays.asList(1, 1, 1, 1, 1)));
  }
  
  public void testRotation_differentSize() {
    assertFalse(Rotation.isRotation(
        Arrays.asList(1, 1, 2, 3, 4), 
        Arrays.asList(1, 1, 2, 3)));
  }
  
  public void testRotation_emptyArray() {
    assertTrue(Rotation.isRotation(
        new ArrayList<Integer>(), 
        new ArrayList<Integer>()));
  }
  
  public void testRotation_fail() {
    assertFalse(Rotation.isRotation(
        Arrays.asList(4, 4, 4, 4), 
        Arrays.asList(1, 1, 1, 1)));
  }
}
