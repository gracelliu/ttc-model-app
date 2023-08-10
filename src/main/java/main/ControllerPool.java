package main;

import controller.employee.EmployeeController;
import controller.stats.StatsController;
import controller.ticket.TicketController;
import controller.train.TrainController;

/**
 * The ControllerPool dataclass represents a collection of a ticketController and a statController.
 */
public class ControllerPool {

    /**
     * The ticket controller.
     */
    private final TicketController ticketController;

    /**
     * The stat controller.
     */
    private final StatsController statController;

    private final EmployeeController employeeController;

    private final TrainController trainController;

    /**
     * Constructs a new ControllerPool with the given ticket and stat controllers.
     *
     * @param pool               The InteractorPool.
     */
    public ControllerPool(InteractorPool pool) {
        this.ticketController = new TicketController(pool.getTicketInteractor());
        this.statController = new StatsController(pool.getStatInteractor());
        this.employeeController = new EmployeeController(pool.getEmployeeInteractor());
        this.trainController = new TrainController(pool.getTrainInteractor());
    }

    /**
     * Gets the ticket controller.
     *
     * @return The ticket controller.
     */
    public TicketController getTicketController() {
        return ticketController;
    }

    /**
     * Gets the stat controller.
     *
     * @return The stat controller.
     */
    public StatsController getStatController() {
        return statController;
    }

    /**
     * Gets the employee controller.
     */
    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public TrainController getTrainController() {
        return trainController;
    }
}
