SAMPLE_PROFILE_SETTINGS = java -XX:+FlightRecorder -XX:StartFlightRecording=settings=profile,filename=sampleprofile_data.jfr

MY_PROFILE_SETTINGS = java -XX:+FlightRecorder -XX:StartFlightRecording=settings=profile,filename=myprofile_data.jfr

junit5:
	javac -cp .:./junit-platform-console-standalone-1.5.2.jar *.java
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path . -p ""

sample_profiler:
	javac SampleProfilerApplication.java
	$(SAMPLE_PROFILE_SETTINGS) SampleProfilerApplication 10000000

my_profiler:
	javac MyProfiler.java
	$(MY_PROFILE_SETTINGS) MyProfiler 10000000

clean:
	\rm -f *.class
	\rm -f *.jfr
