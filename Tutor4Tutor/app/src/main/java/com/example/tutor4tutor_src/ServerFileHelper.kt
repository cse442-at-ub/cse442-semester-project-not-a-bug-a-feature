package com.example.tutor4tutor_src

import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import java.io.IOException
import java.io.InputStream
import java.sql.DriverManager
public class ServerFileHelper : AsyncTask<String, Int, String>() {
    //"getfiles""username"
    override fun doInBackground(vararg params: String): String {
        var jsch = JSch();
        val username = "wyang34";
        val password = "Hbxnywx1995.";
        val remoteHost = "cheshire.cse.buffalo.edu"
        var destPath = "/home/eslgrad/wyang34/Documents"
        val session = jsch.getSession(username, remoteHost, 22);
        session.setConfig("StrictHostKeyChecking", "no")
        session.setPassword(password);
        DriverManager.println("Establishing Connection...");
        session.connect();
        val channel: Channel = session.openChannel("sftp")
        channel.connect()
        val sftpChannel = channel as ChannelSftp
        sftpChannel.cd(destPath)
        var result = ""
        if (params[0] == "get") {
            val filelist = sftpChannel.ls(destPath)
            try {
                for (i in filelist.indices) {
                    val entry = filelist[i] as ChannelSftp.LsEntry
                    val file = entry.filename.split('_')
                    if (file[0] == params[1]) {
                        result= result + file[1]+","
                    }
                }
            } catch (io: IOException) {

            } catch (e: Exception) {
                DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
                e.message
            }
        }
        return result
    }
}