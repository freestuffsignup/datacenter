package thermal;
public class Config {
	final static int EPOCH = 1000;
	final static int BATCH_SIZE = 3;
	final static String FILE = "random_jobs_2";
	final static int TOTAL_SERVER_ROWS = 10;
	final static int TOTAL_SERVER_COLUMNS = 10;
	final static int TOTAL_SERVER_STACKS = 4;
	public final static int TOTAL_CPUs = TOTAL_SERVER_STACKS*TOTAL_SERVER_COLUMNS*TOTAL_SERVER_ROWS;
	final static String TAG = "JOE";
	public final static double MAX_CPU_TEMP = 100.00;
	public final static double MIN_CPU_TEMP = 0.00;
	final static int MAX_PERCENTAGE_USED = 100;
	final static int MIN_PERCENTAGE_USED = 0;
	public final static int MAX_CRAC_POWER_CONSUMPTION = 100;
	public final static int MIN_CRAC_POWER_CONSUMPTION = 00;
}
