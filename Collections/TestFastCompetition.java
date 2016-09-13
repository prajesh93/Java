import java.util.*;
  /*
     *TestFastCompetition.java
     *
     * Version:1.0	  	09/27/2015
     * @ author   		Ashwini Singh
     * @ author   		Prajesh Jhumkhawala
     
     */

interface Competition<E> {
              // Appends the specified element to this storage.
              // Returns true if the element could be added to this storage
             boolean add(E e);
            
             // Returns true if this storage contains the specified element.
              boolean contains(Object o);
              // Removes the first occurrence of the specified element in this storage.
              // If the storage does not contain the element, it is unchanged.
              // Returns true if the element could be removed from this storage
              boolean remove(E o);
              // Returns the component at the specified index.
              E elementAt(int index);
              // Sorts the storage
              // Returns the sorted storage
              Competition<E>  sort();
              // Returns the number of components in this storage.
              int size();
      }
 /**
  * This Class is implementation of Interface Competition
  *
  */

class FastCompetition<E> implements Competition <E>
{ 
	 E[] arrays=(E[])new Object[10000];
	 int flag=0;
	 public FastCompetition(int i) 
     {
    	 
     }
   public boolean add(E e)
   {
		 /**
		  *Appends the specified element to this storage. Returns true if the element could be added to this storage
		  *
		  * @param       e    It is the element to be added
		  *
		  */

	   if(flag< 1000000)
	   {
	   arrays[flag]=e;
	   flag++;
	   return true;
   		}
	   else
		return false;
   }
   
   
    public boolean contains(Object o)
    {
      /**
   	  *Returns true if this storage contains the specified element.
  	  *
  	  * @param       o    Check if the storage contains this object or not
  	  *
  	  */
    	int temp=0;
    	for(int i=0;i<flag;i++)
    	{
    		if(arrays[i].equals(o))
    		{
    			temp=1;
    			break;
    		}
    		else
    			temp=0;
    	}
    return temp==1;
    } 
    public boolean remove(Object o)
    {
    	  /**
      	  *Removes the first occurrence of the specified element in this storage. 
      	  *If the storage does not contain the element, it is unchanged. 
      	  *Returns true if the element could be removed from this storage
     	  *
     	  * @param       o    Remove this object
     	  *
     	  */
       
    	int temp=0;
    	
    	for(int i=0;i<flag;i++)
    	{
    		if(arrays[i].equals(o))
    	{
    	 	for(int k=i;k<flag-1;k++)
    	{
    	 		arrays[k]=arrays[k+1];
    	}
    	 	flag--;
   	 		temp=1;
    	 	break;
    	}
    	}
    	return temp==1;
    }
    
    public E elementAt(int index)
    {
  	  /**
     	  *Returns the component at the specified index. 
     	  *
    	  * @param       index    The element at this index has to be returned
    	  *
    	  */
      
   
    		return arrays[index];
    }
    public Competition<E>  sort()
    {
    	/*
    	 *    Sorts the storage
    	 *    Returns the sorted storage
       	 *
       	 *	
     	 */
    
    
    	int j;
         boolean flag1 = true;   // set flag to true to begin first pass
         
         while ( flag1 )
         {
                flag1= false;    //set flag to false awaiting a possible swap
                for( j=0;  j < 9999;  j++ )
                {	if ( (arrays[j].toString()).compareTo(arrays[j+1].toString())>0)   // change to > for ascending sort
                       {
                             E  temp = arrays[ j ];                //swap elements
                              arrays[ j ] = arrays[ j+1 ];
                               arrays[ j+1 ] = temp;
                              flag1 = true;              //shows a swap occurred  
                      } 
                }
                
          }
         return null;
    	
    }
    
    // Returns the number of components in this storage.
    public int size()
    {
    	/*
    	 * Returns the current sizze of the storage
    	 */
    	return flag;
    }
	
	
}
/*
 * This id the class which was given. We have to check ifthe FastCompetition class compiles with this or not
 *It also contains the main class.
 */
public class TestFastCompetition {
	FastCompetition<String> aStringStorage;
              public TestFastCompetition()    {
              }

              private void failure(String reason)     {
                      System.err.println("You should never see this.");
                      System.err.println("Your program did not pass the test");
                      System.err.println("Reason: " + reason);
                      System.exit(0);
              }
              private void print(String reason)       {
                      System.err.println("Reason: " + reason);
              }
              private void addTest()  {
                      for ( int index = 0; index < 10000; index ++ )  {
                              if ( ! (aStringStorage.add("hello"+ index) ) )
                                      failure("add");
                              if ( aStringStorage.size() != index+1 )
                                      failure("size");

                      }
              }
              private void containTest()      {
                      for ( int index = 0; index < 10000; index ++ )  {
                              if ( ! (aStringStorage.contains("hello"+ index) ) )
                                      failure("contains");
                      }
              }
              private void sortTest() {
                      aStringStorage.sort();

                      for ( int index = 0; index < 10000 - 1; index ++ )      {
                              String thisOne = aStringStorage.elementAt(index);
                              String nextOne = aStringStorage.elementAt(index+1);
                              if ( thisOne.compareTo(nextOne) > 0 )
                                      failure("sortTest");
                      }
              }
              private void removeTest()       {
                      for ( int index = 0; index < 10000; index ++ )  {
                              if ( ! (aStringStorage.remove("hello" + index)  ) )
                                      failure("remove " + index);
                              if ( aStringStorage.size() != 10000 - index -1)
                                      failure("remove.size");
                      }
                      if ( ! (aStringStorage.contains("hello" + 1)  ) )
                              print("contains - expected");
                      if ( ! (aStringStorage.remove("hello" + 1)  ) )
                              print("remove - expected");

            }
              private void stressTest( FastCompetition<String> aStringStorage)        {
                      long startTime = System.currentTimeMillis();

                      this.aStringStorage = aStringStorage;

                      addTest();
                      containTest();
                      sortTest();
                      removeTest();

                      long endTime = System.currentTimeMillis();
                      System.out.println("Total TIme is "+ (endTime-startTime));

              }

              public static void main(String args[] )     {
                      TestFastCompetition aTestFastCompetition = new TestFastCompetition();
                      FastCompetition<String> aFastCompetition = new FastCompetition<String>(10000000);
                      aTestFastCompetition.stressTest(aFastCompetition);

              }
}