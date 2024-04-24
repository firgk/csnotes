import java.util.Scanner;

class File {
    private String name;
    private int parentIndex;
    private String content;

    public File(String name, int parentIndex) {
        this.name = name;
        this.parentIndex = parentIndex;
        this.content = "";
    }

    public String getName() {
        return name;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class FileSystem {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_FILES = 100;
    private static final int MAX_FILE_CONTENT_LENGTH = 1000;

    private File[] files;
    private int fileCount;

    public FileSystem() {
        files = new File[MAX_FILES];
        fileCount = 0;
    }

    public int createFile(String fileName, int parentIndex) {
        if (fileCount >= MAX_FILES) {
            System.out.println("文件系统最大数量满了.");
            return -1;
        }

        if (fileName.length() >= MAX_NAME_LENGTH) {
            System.out.println("文件太长了.");
            return -1;
        }

        for (int i = 0; i < fileCount; i++) {
            if (files[i].getName().equals(fileName) && files[i].getParentIndex() == parentIndex) {
                System.out.println("文件已经在该目录下存在同名的了.");
                return -1;
            }
        }

        File newFile = new File(fileName, parentIndex);
        files[fileCount] = newFile;
        fileCount++;

        return fileCount - 1;
    }

    public int findFile(String fileName, int parentIndex) {
        for (int i = 0; i < fileCount; i++) {
            if (files[i].getName().equals(fileName) && files[i].getParentIndex() == parentIndex) {
                return i;
            }
        }

        return -1;
    }

    public void listFiles(int parentIndex) {
        for (int i = 0; i < fileCount; i++) {
            if (files[i].getParentIndex() == parentIndex) {
                System.out.println(files[i].getName());
            }
        }
    }

    public void readFile(int fileIndex) {
        if (fileIndex < 0 || fileIndex >= fileCount) {
            System.out.println("Invalid file index.");
            return;
        }

        System.out.println("Reading file: " + files[fileIndex].getName());
        System.out.println("File content:\n" + files[fileIndex].getContent());
    }

    public void openFile(int fileIndex) {
        System.out.println("Opening file: " + name);
        // Perform operations on the file
        System.out.println("File content:\n" + content);
    }

    public void writeFile(int fileIndex, String content) {
        if (fileIndex < 0 || fileIndex >= fileCount) {
            System.out.println("Invalid file index.");
            return;
        }

        System.out.println("Writing to file: " + files[fileIndex].getName());
        files[fileIndex].setContent(content);
    }
}

class Directory {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_FILES = 100;

    private String name;
    private int parentIndex;
    private int fileCount;

    public Directory(String name, int parentIndex) {
        this.name = name;
        this.parentIndex = parentIndex;
        this.fileCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }
}

class DirectorySystem {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_FILES = 100;


    private Directory[] directories;
    private int directoryCount;

    public DirectorySystem() {
        directories = new Directory[MAX_FILES];
        directoryCount = 0;
    }

    public int createDirectory(String directoryName, int parentIndex) {
        if (directoryCount >= MAX_FILES) {
            System.out.println("Directory system is full.");
            return -1;
        }

        if (directoryName.length() >= MAX_NAME_LENGTH) {
            System.out.println("Directory name is too long.");
            return -1;
        }

        for (int i = 0; i < directoryCount; i++) {
            if (directories[i].getName().equals(directoryName) && directories[i].getParentIndex() == parentIndex) {
                System.out.println("Directory with the same name already exists in the parent directory.");
                return -1;
            }
        }

        Directory newDirectory = new Directory(directoryName, parentIndex);
        directories[directoryCount] = newDirectory;
        directoryCount++;

        return directoryCount - 1;
    }

    public int findDirectory(String directoryName, int parentIndex) {
        for (int i = 0; i < directoryCount; i++) {
            if (directories[i].getName().equals(directoryName) && directories[i].getParentIndex() == parentIndex) {
                return i;
            }
        }

        return -1;
    }

    public int changeDirectory(int currentDirectoryIndex, String directoryName) {
        if (directoryName.equals("..")) {
            // 返回上一级目录
            return directories[currentDirectoryIndex].getParentIndex();
        } else {
            int directoryIndex = findDirectory(directoryName, currentDirectoryIndex);
            if (directoryIndex != -1) {
                return directoryIndex;
            } else {
                System.out.println("Directory not found.");
                return currentDirectoryIndex;
            }
        }
    }

    public void listDirectoryContents(FileSystem fs, int currentDirectoryIndex) {
        System.out.println("Contents of the current directory:");
        fs.listFiles(currentDirectoryIndex);
    }
}

public class Main {
    private static final int MAX_NAME_LENGTH = 100;
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        DirectorySystem ds = new DirectorySystem();
        int currentDirectoryIndex = 0; // Root directory index

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("输入一个指令 (create, open, read, write, ls, cd, mkdir, exit): ");
            String command = scanner.next();

            if (command.equals("create")) {
                String fileName = scanner.next();
                fs.createFile(fileName, currentDirectoryIndex);
            } else if (command.equals("open")) {
                String fileName = scanner.next();
                int fileIndex = fs.findFile(fileName, currentDirectoryIndex);
                if (fileIndex != -1) {
                    fs.openFile(fileIndex);
                } else {
                    System.out.println("File not found.");
                }
            } else if (command.equals("read")) {
                String fileName = scanner.next();
                int fileIndex = fs.findFile(fileName, currentDirectoryIndex);
                if (fileIndex != -1) {
                    fs.readFile(fileIndex);
                } else {
                    System.out.println("File not found.");
                }
            } else if (command.equals("write")) {
                String fileName = scanner.next();
                int fileIndex = fs.findFile(fileName,currentDirectoryIndex);
                if (fileIndex != -1) {
                    System.out.print("输入文件的内容: ");
                    scanner.nextLine(); // Consume the newline character
                    String content = scanner.nextLine();
                    fs.writeFile(fileIndex, content);
                    System.out.println("文件成功写入.");
                } else {
                    System.out.println("文件未找到.");
                }
            } else if (command.equals("ls")) {
                ds.listDirectoryContents(fs, currentDirectoryIndex);
            } else if (command.equals("cd")) {
                String directoryName = scanner.next();
                currentDirectoryIndex = ds.changeDirectory(currentDirectoryIndex, directoryName);
            } else if (command.equals("mkdir")) {
                String directoryName = scanner.next();
                int directoryIndex = ds.createDirectory(directoryName, currentDirectoryIndex);
                if (directoryIndex != -1) {
                    System.out.println("Directory created with index: " + directoryIndex);
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("非法的指令.");
            } System.out.println();
        }

        scanner.close();
    }
}