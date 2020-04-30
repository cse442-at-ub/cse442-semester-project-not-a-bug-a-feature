package com.example.tutor4tutor_src


import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import org.json.JSONObject
import java.io.*
import java.sql.DriverManager
public class DatabaseHelper : AsyncTask<String, Int, String>() {
    override fun doInBackground(vararg params: String): String? {
        // make connection
        var jsch = JSch();
        val username = "wyang34"
        val remoteHost = "cheshire.cse.buffalo.edu"
        val password = "Hbxnywx1995."
        var filepath = params[1]
        var destPath = "/home/eslgrad/wyang34/Documents"
        val session = jsch.getSession(username, remoteHost, 22)
        session.setConfig("StrictHostKeyChecking", "no")
        session.setPassword(password);
        DriverManager.println("Establishing Connection...");
        session.connect();
        val channel: Channel = session.openChannel("sftp")
        channel.connect()
        val sftpChannel = channel as ChannelSftp
        sftpChannel.cd(destPath)
        //val stream: InputStream = sftpChannel.get(destPath + filepath)
        var isContainFile = false
        var result = ""
        val filelist = sftpChannel.ls(destPath)
        val mstream: InputStream = sftpChannel.get(destPath)
        try {
            for (i in filelist.indices) {
                val entry = filelist[i] as ChannelSftp.LsEntry
                val file = entry.filename
                if (file == params[1]) {
                    isContainFile = true
                }
            }
        } catch (io: IOException) {
        }
        //if get the file doesn't exist, return null, if set file doesn't exist the create the file then set.
        if (!isContainFile)
        {
            if (params[0] == "get")
            {}
            else if (params[0] == "set") {
                sftpChannel.put(destPath + "/" + params[1])
            }
        }
        var overwrite = ""
        var userdata = ""
        var isContainUser = false
        val stream = sftpChannel.get(destPath + "/"+filepath)
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                DriverManager.println(line)
                var user = mysplite(line)
                if (!user.isEmpty() && user[0] == params[2]) {
                    if (params[0]=="set") {
                        overwrite = overwrite +params[2]+"," + params[3] + "\n"
                        isContainUser = true
                    }
                    else if(params[0] == "get") {
                        userdata = user[1]
                    }
                }
                else overwrite = overwrite + line +"\n"
            }
        } catch (io: IOException) {
            System.out.println("Exception occurred during reading file from SFTP server due to ")
        } catch (e: Exception) {
            DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        if ( !isContainUser && params[0] == "set")
            sftpChannel.put(ByteArrayInputStream((params[2] + ',' + params[3] + "\n").toByteArray()), destPath + "/" + filepath , ChannelSftp.APPEND);
        else if(isContainUser && params[0]=="set")
            sftpChannel.put(ByteArrayInputStream(overwrite.toByteArray()), destPath + "/" + filepath, ChannelSftp.OVERWRITE);
        else if(params[0] =="get" && isContainFile)
            return userdata
        return userdata
    }

    fun mysplite(line:String):List<String>
    {
        var usernasme = ""
        var data = ""
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
        data = line.substring(usernasme.count()+1,line.count())
        arrlist+=usernasme
        arrlist+=data
        return arrlist
    }
}