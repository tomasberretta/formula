package edu.austral.ingsis.math;

import java.util.List;

public interface Function {
    Double solve();
    List<String> getVariables(List<String> variables);
    List<String> getVariables();
    String print(String prev);
    String print();
    boolean isComposite();
}
