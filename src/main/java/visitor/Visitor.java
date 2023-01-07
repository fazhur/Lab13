package visitor;

public interface Visitor {
    void visitSignature(Signature signature);
    void visitGroup(Group group);
}
