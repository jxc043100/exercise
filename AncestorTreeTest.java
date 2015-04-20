import junit.framework.TestCase;


/**
 * Test cases for ancestor tree.
 */
public class AncestorTreeTest extends TestCase {
  AncestorTree tree;
  
  @Override
  public void setUp() {
    tree = new AncestorTree();
  }
  
  public void testAddPerson() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    assertTrue(tree.addAncestor("a.mom", "a.mom.mom", "a.mom.dad"));
    assertTrue(tree.addAncestor("a.dad", "a.dad.mom", "UNKNOWN"));

    assertEquals("a", tree.root.personName);
    assertEquals("a.mom", tree.root.mother.personName);
    assertEquals("a.dad", tree.root.father.personName);
    assertEquals("a.mom.mom", tree.root.mother.mother.personName);
    assertEquals("a.mom.dad", tree.root.mother.father.personName);
    assertEquals("a.dad.mom", tree.root.father.mother.personName);
    assertEquals(null, tree.root.father.father);
  }
  
  public void testAddPerson_personNotFound() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    
    assertFalse("c should not be found in the tree", 
        tree.addAncestor("c", "c.mom", "c.dad"));
  }
  
  public void testAddPerson_inconsistent() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    try {
      tree.addAncestor("a", "newMom", "UNKOWN");
      fail("Should fail because overwriting mother");
    } catch (IllegalArgumentException e) {
      
    }
  }
  
  public void testFindPerson() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    assertTrue(tree.addAncestor("a.mom", "a.mom.mom", "a.mom.dad"));
    assertTrue(tree.addAncestor("a.dad", "a.dad.mom", "a.dad.dad"));
    
    AncestorTree.PersonNode aMom = tree.findPerson("a.mom");
    AncestorTree.PersonNode aMomMom = tree.findPerson("a.mom.mom");
    assertEquals("a.mom", aMom.personName);
    assertEquals("a.mom.mom", aMomMom.personName);
  }
  
  public void testFindPerson_notFound() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    assertTrue(tree.addAncestor("a.mom", "a.mom.mom", "a.mom.dad"));
    assertTrue(tree.addAncestor("a.dad", "a.dad.mom", "a.dad.dad"));
    
    assertNull(tree.findPerson("notExist"));
  }
  
  public void testIsAncestor() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    assertTrue(tree.addAncestor("a.mom", "a.mom.mom", "a.mom.dad"));
    assertTrue(tree.addAncestor("a.dad", "a.dad.mom", "a.dad.dad"));
    
    assertNull(tree.findPerson("notExist"));
  }
  
  public void testIsDescendent() {
    assertTrue(tree.addAncestor("a", "a.mom", "a.dad"));
    assertTrue(tree.addAncestor("a.mom", "a.mom.mom", "a.mom.dad"));
    assertTrue(tree.addAncestor("a.dad", "a.dad.mom", "a.dad.dad"));
    
    assertEquals(2, tree.isAncestor("a", "a.mom.mom"));
    assertEquals(1, tree.isAncestor("a", "a.dad"));
    assertEquals(-1, tree.isAncestor("a.mom", "a"));
    assertEquals(-1, tree.isAncestor("a", "a"));
    assertEquals(-1, tree.isAncestor("a", "b"));
  }
}
