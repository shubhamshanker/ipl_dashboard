// package ww.shubham.ipldashboard.data;

// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.JobParametersBuilder;
// import org.springframework.batch.core.launch.JobLauncher;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class BatchJobRunnerConfig {

//     @Autowired
//     private JobLauncher jobLauncher;

//     @Autowired
//     private Job job;

//     @Bean
//     public CommandLineRunner runJob() {
//         return args -> {
//             try {
//                 jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         };
//     }
// }
