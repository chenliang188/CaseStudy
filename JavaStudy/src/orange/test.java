package orange;

public class test
{
//    static boolean foo (char c)
//    {
//        System.out.println(c);
//        return true;
//    }
    private static int i =0;
    
    public static void print ()
    {
        System.out.println("1"+i);
        
        i++;
        
        System.out.println("2"+i);
        add(i);
        
        System.out.println("3"+i);
        add();
        
        System.out.println("4"+i);
        
    }
    
    private static void add(int j)
    {
        j++;
    }
    
    private static void add()
    {
        i++;
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
//        int a = 0;
//        int c = 0;
//        do
//        {
//            --c;
//            a = a-1;
//        } while (a>0);
//        System.out.println(c);
          
        
//        int i = 0;
//        for(foo('A'); foo('B')&&(i<2);foo('C'))
//        {
//            i++;
//            foo('D');
//        }
        
          //test.print();
        
//          int i = 6,j =8,k=10,m=7;
//          
//          if(!(i>j || m>k++)) 
//              System.out.println(k++);
//          System.out.println(++k);
        
        //float f = 3.4;
        
//        int a = 2;
//        switch (a)
//        {
//            case 2:
//                System.out.println("2");
//            case 3:
//                System.out.println("3");
//            default:
//                System.out.println("end");
//                break;
//        }
    }
    
}
