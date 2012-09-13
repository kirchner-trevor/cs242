package cs242.kirchne5.mazesolvinglibrary.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DefaultSolverTest.class, DijkstraSolverTest.class,
		EstimatorSolverTest.class, MazeTests.class, NodeTests.class })
public class AllTests {

}
