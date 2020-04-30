package com.example.tutor4tutor_src

import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import java.io.*
import java.sql.DriverManager


public class UserProfileTask : AsyncTask<String, Int, String>() {

    // username, datatype,
    override fun doInBackground(vararg params: String): String {
        var jsch = JSch();
        val username = "wyang34";
        val remoteHost = "cheshire.cse.buffalo.edu"
        val password = "Hbxnywx1995.";
        val filepath = "/userprofile.txt"
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
        val stream: InputStream = sftpChannel.get(destPath+filepath)
        var overwrite =""
        var userdata = ""
        var isCOntainUser = false
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                DriverManager.println(line)
                var user = mysplite(line)
                if (!user.isEmpty() && user[0] == UserStates.username) {
                    if (params[0]=="set") {
                        overwrite = overwrite +params[1]+"," + params[2] + "\n"
                        isCOntainUser = true
                    }
                    else if(params[0] == "get")
                    {
                        userdata = user[1]
                    }
                }
                else overwrite = overwrite + line +"\n"
            }
        } catch (io: IOException) {

        } catch (e: Exception) {
            DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        if(isCOntainUser == false && params[0]=="set")
            sftpChannel.put(ByteArrayInputStream((params[1]+','+params[2]+"\n").toByteArray()), "/home/eslgrad/wyang34/Documents/userprofile.txt", ChannelSftp.APPEND);
        else if(isCOntainUser == true && params[0]=="set")
            sftpChannel.put(ByteArrayInputStream(overwrite.toByteArray()), "/home/eslgrad/wyang34/Documents/userprofile.txt", ChannelSftp.OVERWRITE);
        return userdata
    }
    fun editData(orignal:String,data:Map<Int,String>):String
    {
        var tmp:String = ""
        var arr = orignal.split(",")
        for (i in 0..arr.count()-1)
        {

            var strtoadd = data.get(i).toString()
            tmp = tmp + strtoadd+","
        }
        //tmp = tmp.substring(0,tmp.count()-1)
        return tmp
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
enum class DataType()
{
    USERNAME,ABOUTME,MAJOR,IDENTITY
}
