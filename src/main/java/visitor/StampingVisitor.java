package visitor;

public class StampingVisitor implements Visitor {
    @Override
    public void visitSignature(Signature signature) {
        signature.apply(this);
        signature.setHeader("signature", "stamp");
    }

    @Override
    public void visitGroup(Group group) {
        for (Object task: group.getTasks()) {
            ((Task) task).apply(this);
        }
        group.setHeader("group", "stamp");
    }
}