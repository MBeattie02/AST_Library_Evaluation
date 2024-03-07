package javaparser.javaparser;

import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.YamlPrinter;

public class PrintAST {
	
	public static void main(String[] args) {
		 String sourceCode = "public class HelloWorld {" +
	                "    public void sayHello(String name) {" +
	                "    }" +
	                "}";

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> parseResult = javaParser.parse(sourceCode);
        Optional<CompilationUnit> compilationUnitOptional = parseResult.getResult();

        if (compilationUnitOptional.isPresent()) {
            CompilationUnit cu = compilationUnitOptional.get();

            // Use YamlPrinter to print the CompilationUnit in YAML format
            YamlPrinter printer = new YamlPrinter(true);
            System.out.println(printer.output(cu));
        } else {
            System.out.println("Parsing failed.");
        }
    }

}
