package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void testHello(){
    	App app = new App();
    	app.hello();
    	assertTrue( true );
    }
    
    @Test
    public void testLoop(){
    	App app = new App();
    	app.loop();
    	while (true) {
			try {
				Thread.sleep(10000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	//assertTrue( true );
    }
}
