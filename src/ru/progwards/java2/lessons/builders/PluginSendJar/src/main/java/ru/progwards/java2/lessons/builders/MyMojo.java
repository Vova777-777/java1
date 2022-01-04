package ru.progwards.java2.lessons.builders;



import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 */
@Mojo( name = "touch", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class MyMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private File outputDirectory;
    @Parameter(/*property = "emailTo", */required = true)
    private String emailTo;
    @Parameter(/*property = "emailFrom", */required = true)
    private String emailFrom;
    @Parameter(/*property = "authServ", */required = true)
    private String authServ;
    @Parameter(/*property = "authUser", */required = true)
    private String authUser;
    @Parameter(/*property = "authPass", */required = true)
    private String authPass;


    public void execute()
        throws MojoExecutionException
    {
        Integer port = 465;
        WorkerMail workerMail = new WorkerMail(emailFrom, authServ, port, authPass);
        ReaderMetaInf readerMetaInf = new ReaderMetaInf();//ReaderMetaInf readerMetaInf = new ReaderMetaInf(dir);
        try {
            workerMail.sendMailTextAndFile(readerMetaInf.readMetaInf(), readerMetaInf.findStrPathJar(), emailTo);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
