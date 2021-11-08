package application;

public class ProcessStatement {

    public void processResponse(String action) {
        switch (action) {
            case "1": case "2": case "3":
                new CreateProcess().create(action);
                break;
            case "5": case "6": case "7": case "8":
                new ReadProcess().read(action);
                break;
            case "9": case "10": case "11":
                new UpdateProcess().update(action);
                break;
            case "12": case "13": case "14":
                new DeleteProcess().delete(action);
                break;
            default:
                throw new RuntimeException("Sorry no such action");
        }
    }
}
