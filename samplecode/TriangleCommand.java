public class TriangleCommand extends Command {
    private Triangle triangle;
    private Model model;

    public TriangleCommand(Triangle triangle, Model model) {
        this.triangle = triangle;
        this.model = model;
    }

    @Override
    public void execute() {
        model.addItem(triangle); // Add the triangle to the model
    }

    @Override
    public void undo() {
        model.removeItem(triangle); // Remove the triangle from the model
    }

    @Override
    public void redo() {
        model.addItem(triangle); // Re-add the triangle to the model
    }
}
