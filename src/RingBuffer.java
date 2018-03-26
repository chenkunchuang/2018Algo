/*
*  This class is implementing ring buffer which is non-blocking queue
*  Put to the tail, take from head so for consumer/producer will not need to
*   be blocked when either one is touch queue
*   NOTE: need to keep the last location as empty so it can determine
*   Full: (tail+1)%MAX_SIZE==head
*   Empty: tail==head
 */

public class RingBuffer {
}
