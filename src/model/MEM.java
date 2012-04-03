package model;

import interfaces.IMemoryAccess;
import interfaces.IInstruction;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
//import model.Memory;

public class MEM implements IMemoryAccess
{
  private int bufferSize;
  private int stallCycles;    //stalls the MEM unit for the number of cycles specified
  private boolean isPreBuffFull;
  private int currentInstrIndex;    //holds the index in the preMEMBuffer of the current instruction
  private PreMEMBufferEntry[] preMEMBuffer;
  private PostMEMBufferEntry postMEMBuffer;
  private PreMEMBufferEntry currentInstruction;
  //private Memory memList;
  //private List<Integer> memList = new ArrayList<Integer>();
  private List<Integer> memList;
  
  /**
   * Class constructor the pre-MEM buffer size as a parameter
   * as parameters
  */
  //public MEM(Memory mem, int buffSize)
  public MEM(List<Integer> mem, int buffSize) 
  {
    stallCycles = 0;
    bufferSize = buffSize;
    memList = mem;
    isPreBuffFull = false; 
    currentInstrIndex = 0;
    preMEMBuffer = new PreMEMBufferEntry[bufferSize];
    for (int i = 0; i < bufferSize; i++){
      preMEMBuffer[i] = new PreMEMBufferEntry();
    }
    postMEMBuffer = new PostMEMBufferEntry();
    currentInstruction =  new PreMEMBufferEntry();
  }
  

  /**
   * Method to process a clock cycle. If stallCycles is greater than 0, this method will just
   * decrement the stallCycle variable then wait for the next clock cycle. If 0, this
   * method will perform the operation of the current instruction
  */
  public void processClockCycle()
  {
    //if there is not an instruction being processed, get the next one from the buffer
    if (currentInstruction.opName == "") {
        currentInstruction = getNextInstruction();
        stallCycles = currentInstruction.numCycles - 1;
      }
    
    if (stallCycles == 0) {
      //go ahead and process the instruction
      String tmpStr = currentInstruction.opName;
      
      if (tmpStr == "lw") {
        double memValue = memList.get(currentInstruction.rt + currentInstruction.immediate);
        //add the result to the post MEM buffer
        addToPostMEM(memValue);
      }
      else if (tmpStr == "sw") {
        memList.set((currentInstruction.rt + currentInstruction.immediate),currentInstruction.rs);
      }
      //add more instructions here
      
      //clear the current instruction
      currentInstruction = new PreMEMBufferEntry();
      
    }
    else {
      //just decrement the stallCycles and wait for the next clock cycle
      stallCycles--;
    }
  }  //end processClockCycle
  
  
  
  /**
   * Method to add an instruction to the pre-MEM buffer. Returns 0 if the 
   * instruction was successfully added, otherwise returns -1 if buffer is full
   */
  public int addToPreMEM(String opName, int seq, int rs, int rt, int imm, int cycles)
  {
    for (int i = 0; i < preMEMBuffer.length; i++) {  
      if (preMEMBuffer[i].opName == "") {       //then add the new instruction here
        preMEMBuffer[i].opName = opName;
        preMEMBuffer[i].progSequenceNumber = seq;
        preMEMBuffer[i].rs = rs;
        preMEMBuffer[i].rt = rt;
        preMEMBuffer[i].immediate = imm;
        preMEMBuffer[i].numCycles = cycles;
        return 0;
      }
    }
    return -1;
  }
  
  
  /**
   * Method to get the next instruction from the pre-MEM buffer
   */
  private PreMEMBufferEntry getNextInstruction()
  {
    //take the first entry from the buffer
    PreMEMBufferEntry tmpInstruction = new PreMEMBufferEntry(preMEMBuffer[0]);
    
    //move the remaining buffer entries forward
    for (int i = 0; i < preMEMBuffer.length - 1; i++) {
        preMEMBuffer[i] = preMEMBuffer[i + 1];
    }
    preMEMBuffer[preMEMBuffer.length - 1] = new PreMEMBufferEntry();
        
    return tmpInstruction;
  }
  
  
  /**
   * Method to add a completed instruction to the post-MEM buffer. Return 0 if successful,
   * return -1 if the buffer is full
   */
  private int addToPostMEM(double memValue)
  {
    if (postMEMBuffer.progSequenceNumber != 0) {
      postMEMBuffer.progSequenceNumber = currentInstruction.progSequenceNumber;
      postMEMBuffer.destinationRegister = currentInstruction.rs;
      postMEMBuffer.opResult = memValue;
      return 0;
    }
    else {
      return -1;
    }
  }
  
  
  /**
   * Method to get the program sequence number from the post-MEM buffer. Returns the program
   * sequence number of the most recently completed instruction or if the buffer
   * is empty the method returns -1. The clear parameter set to true will empty the 
   * buffer, false will leave the value in the buffer
   * Will be used by the Writeback unit
   */
  public int getPostMEMSequenceNum(boolean clear)
  {
    int tmpSeqNum = postMEMBuffer.progSequenceNumber;
    
    if (tmpSeqNum >= 0 && clear == true) {
      postMEMBuffer.progSequenceNumber = -1;
    }
   
    return tmpSeqNum;
  }
  
  
  /**
   * Method to get the destination register number from the post-MEM buffer. 
   * Will be used by the Writeback unit
   */
  public int getPostMEMDestReg()
  {
    return postMEMBuffer.destinationRegister;
  }
  
  
  /**
   * Method to get the operation result from the post-MEM buffer. 
   * Will be used by the Writeback unit
   */
  public double getPostMEMOpResult()
  {
    return postMEMBuffer.opResult;
  }
  
  
  /**
   * Method to get the buffer size. Used only for class testing. 
   */
  public int getBufferSize()
  {
    return this.bufferSize;
  }
  
  /**
   * Methods to return contents of the pre MEM buffer. Used only for class testing.
   */
  public String getPreMEMOpName(int index){return this.preMEMBuffer[index].opName; } 
  public int getPreMEMProgSeqNum(int index){return this.preMEMBuffer[index].progSequenceNumber; }  
  public int getPreMEMrsVal(int index){return this.preMEMBuffer[index].rs; }  
  public int getPreMEMrtVal(int index){return this.preMEMBuffer[index].rt; }
  public int getPreMEMNumCycles(int index){return this.preMEMBuffer[index].numCycles; }
  public String getCurrentInstrOpName(){return this.currentInstruction.opName; }
  
   /**
   * Class to store pre-MEM instructions
   */
  private class PreMEMBufferEntry
  {
    private String opName;
    private int progSequenceNumber;
    private int rs;         //to register for load, from register for store
    private int rt;         //register with memory base location
    private int immediate;     //memory offset
    private int numCycles;     //the number of clock cycles this instruction takes
    
    /**
    * Class constructor
    */
    private PreMEMBufferEntry()
    {
      opName = "";
      progSequenceNumber = -1;
      rs = 0;
      rt = 0;
      immediate = 0;
      numCycles = 0;
    }
    
    /**
    * Class copy constructor
    */
    private PreMEMBufferEntry(PreMEMBufferEntry entry)
    {
      opName = entry.opName;
      progSequenceNumber = entry.progSequenceNumber;
      rs = entry.rs;
      rt = entry.rt;
      immediate = entry.immediate;
      numCycles = entry.numCycles;
    }
  }
  
  /**
  * Class to store post-MEM instructions
  */
  private class PostMEMBufferEntry
  {
    private int progSequenceNumber;
    private int destinationRegister;   //register number to store the result
    private double opResult;
    
    private PostMEMBufferEntry()
    {
      progSequenceNumber = -1;
      destinationRegister = 0;
      opResult = 0;
    }
  }

@Override
public String GetStatus() {
	// TODO Auto-generated method stub
	return null;
}





@Override
public List<IInstruction> CurrentInstructions() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Event PropertyChanged(Object aIn_propertyName) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public void LoadWord(int aIn_RD, int aIn_memaddr) {
	// TODO Auto-generated method stub
	
}


@Override
public void StoreWord(int aIn_RS, int aIn_memaddr) {
	// TODO Auto-generated method stub
	
}


@Override
public void Cycle() {
	// TODO Auto-generated method stub
	
}
   
}  //end of class MEM