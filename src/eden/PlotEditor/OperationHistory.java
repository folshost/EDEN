package eden.PlotEditor;

import eden.PlotEditor.PlantOperation;
import java.util.LinkedList;

public class OperationHistory {
    private final LinkedList<PlantOperation> previousSteps = new LinkedList<>(); // stack; removes from bottom to retain limited capacity
    private final LinkedList<PlantOperation> nextSteps = new LinkedList<>(); // stack
    
    private final static int DEFAULT_HISTORY_CAPACITY = 10;
    private final int historyCapacity;
    
    public OperationHistory() {
        historyCapacity = DEFAULT_HISTORY_CAPACITY;
    }
    
    public OperationHistory(int capacity) {
        historyCapacity = capacity;
    }
    
    public void addStep(PlantOperation newStep) {
        if (previousSteps.size() >= historyCapacity) { // ? consider new method for expression
            previousSteps.removeLast(); // make room for new step
        }
        else if (nextStepExists()) {
            nextSteps.clear();    // set new course for the future
        } 
        previousSteps.push(newStep);
    }
    
    public boolean stepBackward() {
        if (previousStepExists()) {
            PlantOperation prevStep = previousSteps.pop();
            prevStep.undo();
            nextSteps.push(prevStep);
            return true;    // return success
        } else {
            return false;    // return failure
        }
    }
    
    public boolean stepForward() {
        if (nextStepExists()) {
            PlantOperation nextStep = nextSteps.pop();
            nextStep.execute();
            previousSteps.push(nextStep);
            return true;    // return success
        } else {
            return false;    // return fail
        }
    }
    
    private boolean previousStepExists() {
        return previousSteps.size() != 0;
    }
    
    private boolean nextStepExists() {
        return nextSteps.size() != 0;
    }
    
    // For accessing history
    
    public PlantOperation getLastStep() {
        return previousSteps.peek();
    }
    
    public LinkedList<PlantOperation> getPreviousSteps() {
        return previousSteps;
    }
    
    public LinkedList<PlantOperation> getNextSteps() {
        return nextSteps;
    }
}