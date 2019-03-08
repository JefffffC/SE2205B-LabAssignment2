import java.lang.*;

public class QueueSimulator{
    public enum Event { ARRIVAL, DEPARTURE };
    private double currTime;
    private double arrivalRate;
    private double serviceTime;
    private double timeForNextArrival;
    private double timeForNextDeparture;
    private double totalSimTime;
    LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
    LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
    private Event e;

    public double getRandTime(double arrivalRate){
        double num, time1, max=1, min=0, randNUM;
        randNUM= Math.random();
        time1= (-1/arrivalRate) * (Math.log(1-randNUM));
        //System.out.println(time1);
        return time1;
    }

    public QueueSimulator(double aR, double servT, double simT){
        arrivalRate = aR; // arrival rate of packets
        serviceTime = servT; // service time of router
        totalSimTime = simT; // amount of time to run simulation for
        currTime = 0; // initial time is set to zero
    }

    public double calcAverageWaitingTime(){
        double waitingTime = 0;
        int totalPackets = 0;
        while (eventQueue.isEmpty() == false) {
            Data nodeData = eventQueue.dequeue(); // dequeue packet data from event queue
            waitingTime += nodeData.getDepartureTime() - nodeData.getArrivalTime(); // add packet time to waiting time
            totalPackets++; // count the packet
        }

        return waitingTime/totalPackets; // find average waiting time per packet
    }

    public double runSimulation(){
        timeForNextArrival = getRandTime(arrivalRate); // use packet arrival rate to calculate a random timeForNextArrival
        timeForNextDeparture = serviceTime + timeForNextArrival; // departure time is calculated
        while (currTime < totalSimTime) { // loop terminates once allotted simulation time is reached
            if ((timeForNextArrival < timeForNextDeparture) || buffer.isEmpty()) { // scenario if next event is ARRIVAL
                currTime += timeForNextArrival; // add to current time to form timestamp
                Data nodeData = new Data(); // creation of Data object to enqueue
                nodeData.setArrivalTime(currTime); // set arrival time of the packet to the timestamp
                buffer.enqueue(nodeData); // add packet to buffer
                timeForNextDeparture -= timeForNextArrival; // calculate time for next departure
                timeForNextArrival = getRandTime(arrivalRate); // calculate next time for arrival
            }
            else { // scenario if next event is DEPARTURE
                currTime += timeForNextDeparture; // add to current time to form timestamp
                Data nodeData = buffer.dequeue(); // dequeue packet from buffer
                nodeData.setDepartureTime(currTime); // set the departure time in the packet's information
                eventQueue.enqueue(nodeData); // record the packet information in the event queue
                timeForNextArrival = timeForNextArrival - timeForNextDeparture; // calculate time for next arrival
                if (buffer.isEmpty()) {
                    timeForNextDeparture = timeForNextArrival + serviceTime; // if buffer is empty, then it is standard calculation
                }
                else {
                    timeForNextDeparture = serviceTime; // if not, then following departure the next departure is proceeding servicing of queued packet
                }
            }
        }

        return calcAverageWaitingTime();
    }
}






