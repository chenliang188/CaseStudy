import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**                                     
 * 终端私钥同步鉴权的RSA加密算法
 * 
 * @author  NJ261 chenliang
 * @version  [版本号, 2011-2-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class CipherUtil
{
    /**
     * * 生成密钥对 *
     *
     * @return KeyPair *
     * @throws EncryptException
     */
    public static KeyPair generateKeyPair() throws Exception
    {
        try
        {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            //                saveKeyPair(keyPair);
            return keyPair;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(String modulus,
            String publicExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA");
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                modulus), new BigInteger(publicExponent));
        try
        {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     * 生成私钥 *
     *
     * @param modulus *
     * @param privateExponent *
     * @return RSAPrivateKey *
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(String modulus,
            String privateExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA");
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(
                modulus), new BigInteger(privateExponent));
        try
        {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
    
//    public static RSAPublicKey generateDpfRSAPublicKey()
//    {
//        try
//        {
//            String mod = new BigInteger(SystemConfig.getCommonData()
//                    .getSysRSAModulus(), 16).toString(10);
//            String pub = new BigInteger(SystemConfig.getCommonData()
//                    .getSysRSAPublicKey(), 16).toString(10);
//            
//            return generateRSAPublicKey(mod, pub);
//        }
//        catch (Exception e)
//        {
//            DebugLogger.inf("generate RSA Public Key failed.", e);
//            return null;
//        }
//    }
    
    /**
     * 加密
     * @param key 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    private static byte[] encrypt(Key key, byte[] data) throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            /*int blockSize = cipher.getBlockSize();//获得加密块大小，如：加密前数据为128个byte，而key_size=1024 加密块大小为127 byte,加密后为128个byte;因此共有2个加密块，第一个127 byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0)
            {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i
                            * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i
                            * blockSize, raw, i * outputSize);
                //这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
                
                i++;
            }*/
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * 简化加密算法，进公钥初始化隐藏。
     * <功能详细描述>
     * @param source
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String RSAEncrypt(RSAPublicKey publicKey, String source)
    {
        try
        {
            //RSAPublicKey recoveryPubKey =  generateDpfRSAPublicKey();
            
            return toHex(encrypt(publicKey, hexStr2Bytes(source)));
        }
        catch (Exception e)
        {
            
            //DebugLogger.inf("RSA Encrypt faild", e);
            return null;
        }
    }
    
    /**
     * 解密
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    private static byte[] decrypt(Key key, byte[] raw) throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(cipher.DECRYPT_MODE, key);
            /*int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            
            while (raw.length - j * blockSize > 0)
            {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();*/
            
            return cipher.doFinal(raw);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * 简化解密算法，进公钥初始化隐藏。
     * <功能详细描述>
     * @param source
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
//    public static String RSADecrypt(String source)
//    {
//        try
//        {
//            //RSAPublicKey recoveryPubKey =  generateDpfRSAPublicKey();
//            
//            //return toHex(decrypt(DP6MemoryData.getPublicKey(), hexStr2Bytes(source)));
//            return new String(decrypt(DP6MemoryData.getPublicKey(),
//                    hexStr2Bytes(source)), "UTF-8");
//        }
//        catch (Exception e)
//        {
//            
//            DebugLogger.inf("RSA Decrypt faild", e);
//            return null;
//        }
//    }
    
    private static String digits = "0123456789ABCDEF";
    
    /**
     * 进二进制数组转化为16进制字符串
     * <功能详细描述>
     * @param 要转换的数组
     * @param 要转换的长度
     * @return 16进制字符串
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;
            
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        
        return buf.toString();
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param data
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
    
    /**
     * 将16进制字符串转换成二进制数字
     * <功能详细描述>
     * @param 16进制的字符串
     * @return [参数说明]
     * 
     * @return byte[] [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static byte[] hexStr2Bytes(String src)
    {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++)
        {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }
    
    private static byte uniteBytes(String src0, String src1)
    {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }
    
    /** Test
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
        public static void main(String[] args)
        {
            // TODO Auto-generated method stub
            //byte[] modBytes = CipherUtil.hexStr2Bytes("8BD42D12BDCBC7C76E5F4D4E79C5AEE06CB56C7CC4621884A2A226FACCBB1C010FE6238879727C77E53CBF1534417420BECE68A95D3C6358D76A9F1A2F011771");
            //byte[] priBytes = CipherUtil.hexStr2Bytes("284A67027375F886DAA2D35FBD6EDCC90609F0CCB2B9A15EF68DCAB591B0888CC71A8BF0338442A9A38331FC5C82478B51A4981EE8072E43669EDD2A734422D1");
            byte[] textBytes = CipherUtil.hexStr2Bytes("1D430E8DDDC92C1359B5C2BDF040D19EB3E38AF346C2CD886D4D96276F433BAE3614112E98DE2CA9AFFBCE59076C7ED17AF9589CDE2BD8487B13ED92E50D2657535F0CC893766F394491EB3887793BD10B03CF49EA659A7E1B578167809638DC196A78C1EF223B91F0633B40326186E8B809FC9795C1644B736C7961709B6735");
            String modBytes = new BigInteger(
                    "008C347BA78337D6686E2F7848729A00A08374B7696E703647E9D491FA0CDC645AB4F2A7270C14DCF2D871E5B70168E944A918763E09E978329E900CAC2504A588BA17366EEA9C9BCF0B2AF6B49BEB31B9037A97FA33FEBF5DBA77AD5EC2271528EBC8EDF8BD89E27786C5A6486F04BB0E3322A392B03C56D9B75DC34460DF166B",
                    16).toString(10);
            String priBytes = new BigInteger(
                    "60F707321209330AE4718621ACC89F786391FC2B398AF7AF82A35615F6811212BDA438404415C5BD5A3E635DCEE6CCB584B0FB4DEAB3024317F08E231C32FB7BEBDAC8ADB91DF2AAD15E7699456D235C702215FC021FC4B81C8609353CD06C6C16E990088B7853E9430827D62BCB67D8A916BDBD40588C422F44856EC74F2EC9",
                    16).toString(10);
            
            try
            {
                RSAPublicKey recoveryPriKey = CipherUtil.generateRSAPublicKey(modBytes,
                        priBytes);
                
                System.out.println(toHex(CipherUtil.decrypt(recoveryPriKey,
                        textBytes)));
                System.out.println(new String(CipherUtil.decrypt(recoveryPriKey,
                        textBytes)));
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
//28DB52B537CD4DDD7A1B7CAF6B83D0EE54F45FD4F96CB49A5CE0AA4A8128D21087B1549678816B5B3EBB381844BE60B8673D12BCF086F57451765563C2B953231D7FE410DC1465237C4D8451475AFA3739654A37C9AD8EB597BD4CB1C35856CE25362C83B1C95CAF14CDC9C74AE5981F59A4B8BF99BE0A92ACF562BC9F874CDE
 
            
//008C347BA78337D6686E2F7848729A00A08374B7696E703647E9D491FA0CDC645AB4F2A7270C14DCF2D871E5B70168E944A918763E09E978329E900CAC2504A588BA17366EEA9C9BCF0B2AF6B49BEB31B9037A97FA33FEBF5DBA77AD5EC2271528EBC8EDF8BD89E27786C5A6486F04BB0E3322A392B03C56D9B75DC34460DF166B
//60F707321209330AE4718621ACC89F786391FC2B398AF7AF82A35615F6811212BDA438404415C5BD5A3E635DCEE6CCB584B0FB4DEAB3024317F08E231C32FB7BEBDAC8ADB91DF2AAD15E7699456D235C702215FC021FC4B81C8609353CD06C6C16E990088B7853E9430827D62BCB67D8A916BDBD40588C422F44856EC74F2EC9
//010001
//1D430E8DDDC92C1359B5C2BDF040D19EB3E38AF346C2CD886D4D96276F433BAE3614112E98DE2CA9AFFBCE59076C7ED17AF9589CDE2BD8487B13ED92E50D2657535F0CC893766F394491EB3887793BD10B03CF49EA659A7E1B578167809638DC196A78C1EF223B91F0633B40326186E8B809FC9795C1644B736C7961709B6735

//            try
//            {
//                KeyPair keypair = CipherUtil.generateKeyPair();
//                
//                RSAPrivateKey prk = (RSAPrivateKey)keypair.getPrivate();
//                
//                RSAPublicKey puk = (RSAPublicKey)keypair.getPublic();
//                
//                System.out.println(toHex(prk.getModulus().toByteArray()));
//                System.out.println(toHex(prk.getPrivateExponent().toByteArray()));
//                System.out.println(toHex(puk.getPublicExponent().toByteArray()));
//                
//                System.out.println(toHex(CipherUtil.encrypt(puk, "chenkljkfkkflaflkafjklasjfd".getBytes())));
//            }
//            catch (Exception e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    
}
