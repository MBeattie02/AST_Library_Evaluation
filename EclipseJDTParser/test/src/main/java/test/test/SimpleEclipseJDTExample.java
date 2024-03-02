package test.test;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class SimpleEclipseJDTExample {

	public static void main(String[] args) {

        // Create a new AST parser
        ASTParser parser = ASTParser.newParser(AST.JLS10);
        parser.setSource((
        		"public class HelloWorld {" +
    	                "    private int count = 0;" +
    	                "    public void sayHello(String name) {" +
    	                "        System.out.println(\"Hello, \" + name);" +
    	                "        this.count++;" +
    	                "    }" +
    	                "    public int getCount() {" +
    	                "        return this.count;" +
    	                "    }" +
    	                "    public void resetCount() {" +
    	                "        this.count = 0;" +
    	                "    }" +
    	                "}").toCharArray());

        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        // Start the timer to measure parsing duration
        long startTime = System.currentTimeMillis();

        // Perform the parsing
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        // Visit nodes in the AST
        cu.accept(new ASTVisitor() {

            // Visit class declarations
            public boolean visit(TypeDeclaration node) {
                System.out.println("Class name: " + node.getName().getIdentifier());
                return super.visit(node);
            }

            // Visit method declarations
            public boolean visit(MethodDeclaration node) {
                SimpleName name = node.getName();
                System.out.println("Method name: " + name.getIdentifier());
                return super.visit(node);
            }

            // Visit field declarations
            public boolean visit(FieldDeclaration node) {
                for (Object fragment : node.fragments()) {
                    VariableDeclarationFragment var = (VariableDeclarationFragment) fragment;
                    System.out.println("Field name: " + var.getName().getIdentifier() + ", Type: " + node.getType());
                }
                return super.visit(node);
            }

            // Visit method call expressions
            public boolean visit(MethodInvocation node) {
                System.out.println("Method call: " + node.getName().getIdentifier());
                return super.visit(node);
            }
        });

        // Stop the timer after parsing and visiting nodes
        long endTime = System.currentTimeMillis();

        // Calculate the duration
        long duration = endTime - startTime;

        // Print out the duration of the parsing and visiting process
        System.out.println("Parsing and visiting duration: " + duration + " milliseconds");
    }
}
