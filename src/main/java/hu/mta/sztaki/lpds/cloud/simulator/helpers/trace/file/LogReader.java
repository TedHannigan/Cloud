package hu.mta.sztaki.lpds.cloud.simulator.helpers.trace.file;

import java.lang.reflect.InvocationTargetException;

import hu.mta.sztaki.lpds.cloud.simulator.helpers.job.Job;

public class LogReader extends TraceFileReaderFoundation {

	public LogReader(String traceKind, String fileName, int from, int to, boolean allowReadingFurther,
			Class<? extends Job> jobType) throws SecurityException, NoSuchMethodException {
		super(traceKind, fileName, from, to, allowReadingFurther, jobType);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isTraceLine(String line) {
		String[] traceline = null;
		if (line != null) {
			traceline = line.split(" ");
			if(traceline.length == 4) {
				return true;
				}
		}
		
		return false;
	}

	@Override
	protected void metaDataCollector(String line) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Job createJobFromLine(String line)
			throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		final String[] fragments = line.trim().split("\\s+");
		/**
		 * String id, long submit, long queue, long exec, int nprocs, double ppCpu, long
		 * ppMem, String user, String group, String executable, Job preceding, long
		 * delayAfter
		 */
		// 1 done, 0 fail, 5 cancel
		int jobState = 0;
		int procs = 0;
		long runtime = 0;
		long waitTime = 0;

		Job preceedingJob = null;
		return jobCreator.newInstance(
				// id:
				fragments[2],
				// submit time in secs:
				Long.parseLong(fragments[0]),
				// wait time in secs:
				waitTime,
				// run time in secs:
				(long)(Double.parseDouble(fragments[1])),
				// allocated processors:
				1,
				// average cpu time:
				-1,
				// average memory:
				-1,
				// userid:
				"USER",
				// groupid:
				"GROUP",
				// execid:
				fragments[3], preceedingJob, 0);
		
	}
	
	

}
