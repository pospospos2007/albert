package com.zdcf.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
 
 
/**
 *
 *@ClassName: TestOSS
 *
 *@Description: 该示例代码展示了如果在OSS中创建和删除一个Bucket，以及如何上传和下载一个文件。
 *
 * 该示例代码的执行过程是：
 * 1. 创建一个Bucket（如果已经存在，则忽略错误码）；
 * 2. 上传一个文件到OSS；
 * 3. 下载这个文件到本地；
 * 4. 清理测试资源：删除Bucket及其中的所有Objects。
 *
 * 尝试运行这段示例代码时需要注意：
 * 1. 为了展示在删除Bucket时除了需要删除其中的Objects,
 *    示例代码最后为删除掉指定的Bucket，因为不要使用您的已经有资源的Bucket进行测试！
 * 2. 请使用您的API授权密钥填充ACCESS_ID和ACCESS_KEY常量；
 * 3. 需要准确上传用的测试文件，并修改常量uploadFilePath为测试文件的路径；
 *    修改常量downloadFilePath为下载文件的路径。
 * 4. 该程序仅为示例代码，仅供参考，并不能保证足够健壮。
 *OSS Java API手册：http://aliyun_portal_storage.oss.aliyuncs.com/oss_api/oss_javahtml/index.html?spm=5176.7150518.1996836753.8.d5TjaG
 *OSS Java SDK开发包:http://help.aliyun.com/view/13438814.html
 *OSSClient:www.mvnrepository.com/artifact/cglib/cglib/2.2
 *@author PineTree
 *@date 2014年12月1日 下午3:23:32
 *@version
 */
public class TestOSS {
    /**
     * 阿里云ACCESS_ID
     */
//	p1XuLXBpDNQ40SGI
    private static String ACCESS_ID = "p1XuLXBpDNQ40SGI";
    /**
     * 阿里云ACCESS_KEY
     */
//    epwi7dXVtDkv1Qxk9dIUNeXa9194p3
    private  static String ACCESS_KEY = "epwi7dXVtDkv1Qxk9dIUNeXa9194p3";
    /**
     * 阿里云OSS_ENDPOINT  青岛Url
     */
//    http://yhcx.oss-cn-beijing.aliyuncs.com
//    http://barlolo.img-cn-hongkong.aliyuncs.com
    private static String OSS_ENDPOINT = "http://yhcx.oss-cn-beijing.aliyuncs.com";
     
    /**
     * 阿里云BUCKET_NAME  OSS
     */
    private static String BUCKET_NAME = "yhcx";
     
     
    public static void main(String[] args) {
        //String bucketName = "demo10";
        String Objectkey = "photo1.jpg";
         
        String uploadFilePath = "D:\\test.jpg";
        String downloadFilePath = "D:\\test1.jpg";
         
        // 使用默认的OSS服务器地址创建OSSClient对象,不叫OSS_ENDPOINT代表使用杭州节点，青岛节点要加上不然包异常
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
         
        //如果你想配置OSSClient的一些细节的参数，可以在构造OSSClient的时候传入ClientConfiguration对象。
        //ClientConfiguration是OSS服务的配置类，可以为客户端配置代理，最大连接数等参数。
        //具体配置看http://aliyun_portal_storage.oss.aliyuncs.com/oss_api/oss_javahtml/OSSClient.html#id2
        //ClientConfiguration conf = new ClientConfiguration();
        //OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, conf);
 
         
        try {
            ensureBucket(client, BUCKET_NAME);
            setBucketPublicReadable(client, BUCKET_NAME);
 
            System.out.println("正在上传...");
            uploadFile(client, BUCKET_NAME, Objectkey, uploadFilePath);
 
//            System.out.println("正在下载...");
//            downloadFile(client, BUCKET_NAME, Objectkey, downloadFilePath);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            deleteBucket(client, BUCKET_NAME);
        }
    }
     
    /**
     * 创建Bucket
     *
     * @param client  OSSClient对象
     * @param bucketName  BUCKET名
     * @throws OSSException
     * @throws ClientException
     */
    public static void ensureBucket(OSSClient client, String bucketName)throws OSSException, ClientException{
        try{
            client.createBucket(bucketName);
        }catch(ServiceException e){
//            if(!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())){
//                throw e;
//            }
        }
    }
     
    /**
     * 删除一个Bucket和其中的Objects
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @throws OSSException
     * @throws ClientException
     */
    private static void deleteBucket(OSSClient client, String bucketName)throws OSSException, ClientException{
        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
        for(int i = 0; i < listDeletes.size(); i++){
            String objectName = listDeletes.get(i).getKey();
            System.out.println("objectName = " + objectName);
            //如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }
     
    /**
     * 把Bucket设置成所有人可读
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @throws OSSException
     * @throws ClientException
     */
    private static void setBucketPublicReadable(OSSClient client, String bucketName)throws OSSException, ClientException{
        //创建bucket
        client.createBucket(bucketName);
         
        //设置bucket的访问权限， public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }
     
    /**
     * 上传文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename  本地文件名
     * @throws OSSException
     * @throws ClientException
     * @throws FileNotFoundException
     */
    private static void uploadFile(OSSClient client, String bucketName, String Objectkey, String filename)
            throws OSSException, ClientException, FileNotFoundException{
        File file = new File(filename);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        //判断上传类型，多的可根据自己需求来判定
        if (filename.endsWith("xml")) {
            objectMeta.setContentType("text/xml");
        }
        else if (filename.endsWith("jpg")) {
            objectMeta.setContentType("image/jpeg");
        }
        else if (filename.endsWith("png")) {
            objectMeta.setContentType("image/png");
        }
         
        InputStream input = new FileInputStream(file);
        client.putObject(bucketName, Objectkey, input, objectMeta);
    }
     
    /**
     *  下载文件
     *
     * @param client  OSSClient对象
     * @param bucketName  Bucket名
     * @param Objectkey  上传到OSS起的名
     * @param filename 文件下载到本地保存的路径
     * @throws OSSException
     * @throws ClientException
     */
    private static void downloadFile(OSSClient client, String bucketName, String Objectkey, String filename)
            throws OSSException, ClientException {
        client.getObject(new GetObjectRequest(bucketName, Objectkey),
                new File(filename));
    }
}