package com.example.tutor4tutor_src

import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.ChannelSftp.LsEntry
import com.jcraft.jsch.JSch
import java.io.*
import java.sql.DriverManager


public class ClassInfoTask : AsyncTask<String, Int, String>() {
    // the input form is "set/get/remove","filename","username","id","data"
    override fun doInBackground(vararg params: String): String {
        var jsch = JSch();
        val username = "wyang34";
        val password = "Hbxnywx1995.";
        val remoteHost = "cheshire.cse.buffalo.edu"
        val filepath = "/"+params[1]
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
        val filelist = sftpChannel.ls(destPath)
        var isContainFile = false
        try {
            for (i in filelist.indices) {
                val entry = filelist[i] as LsEntry
                if(entry.filename == params[1]) {
                    isContainFile = true
                    break
                }
            }
        } catch (io: IOException) {

        } catch (e: Exception) {
            DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        if(!isContainFile)
            sftpChannel.put(destPath+filepath)
        val stream: InputStream =sftpChannel.get(destPath+filepath)
        var overwrite =""
        var userdata:String = ""
        if(params[0]=="set")
            sftpChannel.put(ByteArrayInputStream((params[2]+','+params[3]+","+params[4]+"\n").toByteArray()), "/home/eslgrad/wyang34/Documents/"+params[1], ChannelSftp.APPEND);
        else if (params[0]=="remove" || params[0] == "get")
        {
            try {
                val br = BufferedReader(InputStreamReader(stream))
                var line: String =""
                while (br.readLine().also({ line = it }) != null) {
                    DriverManager.println(line)
                    var info = mysplite(line)
                    if (info[0] == params[2])
                    {
                        var subinfo = mysplite(info[1])
                        if (subinfo[0] == params[3]) {
                            userdata = subinfo[1]
                            continue
                        }
                    }
                    overwrite= overwrite + line+"\n";
                }
            } catch (io: IOException) {

            } catch (e: Exception) {
                DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
                e.message
            }
            if(params[0]=="remove")
                sftpChannel.put(ByteArrayInputStream(overwrite.toByteArray()), "/home/eslgrad/wyang34/Documents/"+params[1], ChannelSftp.OVERWRITE);
        }
        return userdata
    }
    fun mysplite(line:String):List<String>
    {
        var usernasme = ""
        var arrlist = listOf<String>()
        for (item in line)
        {
            if (item != ',')
            {
                usernasme+=item
            }
            else
                break
        }
        var data = line.substring(usernasme.count()+1,line.count())
        arrlist+=usernasme
        arrlist+=data
        return arrlist
    }

}