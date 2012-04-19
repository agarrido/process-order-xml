import java.util.ArrayList;
import java.util.Scanner;

import rdf.VarExtractor;
import sparql.Queries;
import sparql.RunQuery;


public class RunAll {
	
	private ArrayList<String> wfRuns;
	private RunExample r;
	private Queries q;
	private RunQuery rQ;
	private VarExtractor extractor;
	private String finalResult=null;;
	
	public RunAll(){
		q= new Queries();
		rQ = new RunQuery();
		extractor = new VarExtractor();
		wfRuns = new ArrayList<String>();
		r=new RunExample();
	}

	public String run() {
		setWfRuns();
		finalResult = finalResult+"<?xml version=\"1.0\" encoding='iso-8859-1' ?>\n";
		finalResult = finalResult+"<root>\n";
		iterateRuns();
		finalResult = finalResult+"</root>\n";
		//System.out.println(finalResult);
		return finalResult;
	}

	private void iterateRuns() {
		for (String run: wfRuns){
			finalResult=finalResult+r.runExample(run);
		}
		
	}

	private void setWfRuns() {
		String rdf=null;
		rdf=rQ.runQuery(q.getListExecutions());
		//System.out.println(rdf);
		Scanner scanner = new Scanner(rdf);
		String line=null;
		String run=null;
		while (scanner.hasNextLine()) {
		  line = scanner.nextLine();
		  if (extractor.checkAppereance(line, "wfRun")){
			  run=extractor.SimpleExtract(line);
			  wfRuns.add(run);
		  }
		}
		
	}

}
