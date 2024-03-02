package javaparser.javaparser;

import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JavaParserExample {
	 public static void main(String[] args) {
	        // Define complex source code to parse
	        String sourceCode = "public class HelloWorld {" +
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
	                "}";

	        // Start the timer to measure parsing duration
	        long startTime = System.currentTimeMillis();

	        // Create an instance of JavaParser
	        JavaParser javaParser = new JavaParser();

	        // Parse the source code using the instance method
	        ParseResult<CompilationUnit> parseResult = javaParser.parse(sourceCode);

	        // Check if parsing was successful and get the CompilationUnit
	        Optional<CompilationUnit> compilationUnitOptional = parseResult.getResult();

	        if (compilationUnitOptional.isPresent()) {
	            CompilationUnit cu = compilationUnitOptional.get();

	            // Visit and print various elements
	            cu.accept(new ClassVisitor(), null);
	            cu.accept(new MethodVisitor(), null);
	            cu.accept(new FieldVisitor(), null);
	            cu.accept(new MethodCallVisitor(), null);

	            // Stop the timer after parsing and visiting nodes
	            long endTime = System.currentTimeMillis();

	            // Calculate the duration
	            long duration = endTime - startTime;

	            // Print out the duration of the parsing and visiting process
	            System.out.println("Parsing and visiting duration: " + duration + " milliseconds");
	        } else {
	            System.out.println("Parsing failed.");
	        }
	    }

	    /**
	     * Visitor implementation for visiting class declarations.
	     */
	    private static class ClassVisitor extends VoidVisitorAdapter<Void> {
	        @Override
	        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
	            System.out.println("Class name: " + n.getName());
	            super.visit(n, arg);
	        }
	    }

	    /**
	     * Visitor implementation for visiting method declarations.
	     */
	    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
	        @Override
	        public void visit(MethodDeclaration n, Void arg) {
	            System.out.println("Method name: " + n.getName() + ", Return type: " + n.getType());
	            super.visit(n, arg);
	        }
	    }

	    /**
	     * Visitor implementation for visiting field declarations.
	     */
	    private static class FieldVisitor extends VoidVisitorAdapter<Void> {
	        @Override
	        public void visit(FieldDeclaration n, Void arg) {
	            System.out.println("Field name: " + n.getVariables().get(0).getName() + ", Type: " + n.getElementType());
	            super.visit(n, arg);
	        }
	    }

	    /**
	     * Visitor implementation for visiting method call expressions.
	     */
	    private static class MethodCallVisitor extends VoidVisitorAdapter<Void> {
	        @Override
	        public void visit(MethodCallExpr n, Void arg) {
	            System.out.println("Method call: " + n.getName());
	            super.visit(n, arg);
	        }
	    }
}
