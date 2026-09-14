/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

public interface FormulaVisitor <Result, AdditionalArg> {
    Result visit(Not form, AdditionalArg a);
    Result visit(BinaryOperator form, AdditionalArg a);
    Result visit(Atom form, AdditionalArg a);
    Result visit(Constant form, AdditionalArg a);
}
