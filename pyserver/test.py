import pysftp

myHostname = "cheshire.cse.buffalo.edu"
myUsername = "wyang34"
myPassword = "Hbxnywx1995."

def connect():
    with pysftp.Connection(myHostname, myUsername,22,myPassword) as sftp:
        print ("Connection succesfully stablished ... ")
        sftp.cwd('/home/eslgrad/wyang34/Desktop')
        directory_structure = sftp.listdir_attr()
        for attr in directory_structure:
            print (attr.filename, attr)

def upload():
    with pysftp.Connection(myHostname, myUsername,22, myPassword) as sftp:
        print ("Connection succesfully stablished ... ")
    # Define the file that you want to upload from your local directorty
    # or absolute "C:\Users\sdkca\Desktop\TUTORIAL2.txt"
    localFilePath = './TUTORIAL2.txt'
    # Define the remote path where the file will be uploaded
    remoteFilePath = '/home/eslgrad/wyang34/Desktop/TUTORIAL2.txt'
    sftp.put(localFilePath, remoteFilePath)

def download():
    with pysftp.Connection(myHostname, myUsername,22 ,myPassword) as sftp:
        print ("Connection succesfully stablished ... ")

        # Define the file that you want to download from the remote directory
        remoteFilePath = '/var/integraweb-db-backups/TUTORIAL.txt'

        # Define the local path where the file will be saved
        # or absolute "C:\Users\sdkca\Desktop\TUTORIAL.txt"
        localFilePath = './TUTORIAL.txt'

        sftp.get(remoteFilePath, localFilePath)
def deletefile():
    with pysftp.Connection(myHostname, myUsername,22, myPassword) as sftp:
        print("Connection succesfully stablished ... ")

    # Define the file that you want to upload from your local directorty
        sftp.remove('/var/custom-folder/TUTORIAL2.txt')
