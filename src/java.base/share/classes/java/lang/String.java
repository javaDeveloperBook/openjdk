
/*
 * Copyright (c) 1994, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 版权所有（c）1994,2018，Oracle和/或其附属公司。 版权所有。 请勿更改或删除版权声明或此文件标题。
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 * 这段代码是免费软件; 您可以重新分发和/或修改它
 * 根据GNU通用公共许可证版本2的条款，仅作为
 * 由自由软件基金会出版。 Oracle指定了这一点
 * 特定文件作为提供的“Classpath”异常的主题 由Oracle在LICENSE文件中附带此代码。
 *
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 此代码的分发是希望它有用，
 * 但没有任何担保; 甚至没有适销性或特定用途适用性的暗示保证。
 * 有关更多详细信息，请参阅GNU通用公共许可证版本2（副本包含在此代码附带的LICENSE文件中）。
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 您应该已收到GNU通用公共许可证版本2的副本以及此项工作;
 * 如果没有，请写信给Free Software Foundation，Inc.，51 Franklin St，Fifth Floor，Boston，MA 02110-1301 USA。
 *
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 * 如果您需要更多信息或有任何疑问，
 * 请联系Oracle，500 Oracle Parkway，Redwood Shores，CA 94065 USA或访问www.oracle.com。
 */

package java.lang;

import java.io.ObjectStreamField;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Native;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.vm.annotation.Stable;

/**
 * The {@code String} class represents character strings. All
 * string literals in Java programs, such as {@code "abc"}, are
 * implemented as instances of this class.
 *
 * String 类表示字符串。 Java程序中的所有字符串文字（例如“abc”）都是作为此类的实例实现的。
 *
 * <p>
 * Strings are constant; their values cannot be changed after they
 * are created. String buffers support mutable strings.
 * Because String objects are immutable they can be shared. For example:
 *
 * 字符串是不变的; 它们的值在创建后无法更改。
 * 字符串缓冲区支持可变字符串。 因为String对象是不可变的，所以可以共享它们。 例如：
 *
 * <blockquote><pre>
 *     String str = "abc";
 * </pre></blockquote><p>
 * is equivalent to:
 * 相当于：
 * <blockquote><pre>
 *     char data[] = {'a', 'b', 'c'};
 *     String str = new String(data);
 * </pre></blockquote><p>
 *
 * Here are some more examples of how strings can be used:
 * 以下是一些如何使用字符串的示例：
 *
 * <blockquote><pre>
 *     System.out.println("abc");
 *     String cde = "cde";
 *     System.out.println("abc" + cde);
 *     String c = "abc".substring(2,3);
 *     String d = cde.substring(1, 2);
 * </pre></blockquote>
 * <p>
 * The class {@code String} includes methods for examining
 * individual characters of the sequence, for comparing strings, for
 * searching strings, for extracting substrings, and for creating a
 * copy of a string with all characters translated to uppercase or to
 * lowercase. Case mapping is based on the Unicode Standard version
 * specified by the {@link java.lang.Character Character} class.
 *
 * 类 String 包括用于检查序列的各个字符，用于比较字符串，搜索字符串，
 * 提取子字符串以及创建字符串副本的方法，其中所有字符都转换为大写或小写。
 * 案例映射基于 java.lang.Character Character 类指定的Unicode标准版本。
 *
 *
 * <p>
 * The Java language provides special support for the string
 * concatenation operator (&nbsp;+&nbsp;), and for conversion of
 * other objects to strings. For additional information on string
 * concatenation and conversion, see <i>The Java&trade; Language Specification</i>.
 *
 * Java语言为字符串连接运算符 + ，和为其他对象转换为字符串 提供特殊支持
 * 有关字符串连接和转换的其他信息，请参阅<i> Java＆trade; 语言规范</ i>。
 *
 * <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 * or method in this class will cause a {@link NullPointerException} to be
 * thrown.
 *
 * 除非另有说明，否则将 null 参数传递给此类中的构造函数或方法将导致抛出 NullPointerException 异常
 *
 * <p>A {@code String} represents a string in the UTF-16 format
 * in which <em>supplementary characters</em> are represented by <em>surrogate
 * pairs</em> (see the section <a href="Character.html#unicode">Unicode
 * Character Representations</a> in the {@code Character} class for
 * more information).
 *
 * String 表示UTF-16格式的字符串，其中补充字符由代理项对表示（有关详细信息，请参阅 Character 类中的 Unicode 字符表示形式一节）。
 *
 * Index values refer to {@code char} code units, so a supplementary
 * character uses two positions in a {@code String}.
 *
 * 索引值是指 char 代码单元，因此补充字符在 String 中使用两个位置。
 *
 *
 * <p>The {@code String} class provides methods for dealing with
 * Unicode code points (i.e., characters), in addition to those for
 * dealing with Unicode code units (i.e., {@code char} values).
 *
 * String 类提供了处理 Unicode 编码点（即字符）的方法，以及处理 Unicode 编码单元（即char值）的方法。
 *
 *
 * <p>Unless otherwise noted, methods for comparing Strings do not take locale
 * into account.  The {@link java.text.Collator} class provides methods for
 * finer-grain, locale-sensitive String comparison.
 *
 * 除非另有说明，否则比较字符串的方法不会考虑区域设置。 java.text.Collator 类提供了更细粒度，区域敏感的 String 比较方法。
 *
 *
 * @implNote
 * 实施说明：
 *
 * The implementation of the string concatenation operator is left to
 * the discretion of a Java compiler, as long as the compiler ultimately conforms
 * to <i>The Java&trade; Language Specification</i>. For example, the {@code javac} compiler
 * may implement the operator with {@code StringBuffer}, {@code StringBuilder},
 * or {@code java.lang.invoke.StringConcatFactory} depending on the JDK version. The
 * implementation of string conversion is typically through the method {@code toString},
 * defined by {@code Object} and inherited by all classes in Java.
 *
 * 只要编译器最终符合Java™语言规范，字符串连接运算符的实现由Java编译器决定。
 * 例如，javac 编译器可以使用 StringBuffer，StringBuilder 或 java.lang.invoke.StringConcatFactory 实现运算符，具体取决于JDK版本。
 * 字符串转换的实现通常是通过 toString 方法，由 Object 定义并由 Java 中的所有类继承。
 *
 * @author  Lee Boynton 李 博顿
 * @author  Arthur van Hoff 亚瑟 范 霍夫
 * @author  Martin Buchholz 马丁 巴克霍尔兹
 * @author  Ulf Zibis  乌尔夫
 * @see     java.lang.Object#toString()
 * @see     java.lang.StringBuffer
 * @see     java.lang.StringBuilder
 * @see     java.nio.charset.Charset
 * @since   1.0
 * @jls     15.18.1 String Concatenation Operator +
 *                  字符串连接运算符 +
 */

public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {

    /**
     * The value is used for character storage.
     * 该值用于字符存储。
     *
     * @implNote This field is trusted by the VM, and is a subject to
     * constant folding if String instance is constant. Overwriting this
     * field after construction will cause problems.
     *
     * 该字段受VM信任，如果 String 实例是常量，则该字段是常量压缩的主题。 构造后覆盖此字段会导致问题。
     *
     * Additionally, it is marked with {@link Stable} to trust the contents
     * of the array. No other facility in JDK provides this functionality (yet).
     * {@link Stable} is safe here, because value is never null.
     *
     * 此外，它标有 Stable 以信任数组的内容。 JDK 中没有其他工具提供此功能（尚未）。 Stable 在这里是安全的，因为值永远不会为空。
     *
     */
    @Stable
    private final byte[] value;

    /**
     * The identifier of the encoding used to encode the bytes in
     * {@code value}. The supported values in this implementation are
     *
     * 用于编码上面成员变量 value 中字节的编码标识符。 此实现中支持的值是 LATIN1 、UTF16 两种编码
     *
     * LATIN1
     * UTF16
     *
     * @implNote This field is trusted by the VM, and is a subject to
     * constant folding if String instance is constant. Overwriting this
     * field after construction will cause problems.
     *
     * 该字段受 VM 信任，如果 String 实例是常量，则该字段是常量压缩的主题。 构造后覆盖此字段会导致问题。
     *
     */
    private final byte coder;

    /** Cache the hash code for the string  缓存字符串的哈希码，默认是 0  */
    private int hash; // Default to 0

    /** use serialVersionUID from JDK 1.0.2 for interoperability
     *  使用JDK 1.0.2 中的 serialVersionUID 实现互操作性
     */
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * If String compaction is disabled, the bytes in {@code value} are
     * always encoded in UTF16.
     *
     * 如果禁用字符串压缩，则 value 中的字节始终以 UTF16 编码。
     *
     * For methods with several possible implementation paths, when String
     * compaction is disabled, only one code path is taken.
     *
     * 对于具有多个可能实现路径的方法，禁用 String 压缩时，仅采用一个代码路径。
     *
     *
     * The instance field value is generally opaque to optimizing JIT compilers.
     * 实例字段值通常对优化 JIT 编译器不透明。
     *
     * Therefore, in performance-sensitive place, an explicit
     * check of the static boolean {@code COMPACT_STRINGS} is done first
     * before checking the {@code coder} field since the static boolean
     * {@code COMPACT_STRINGS} would be constant folded away by an
     * optimizing JIT compiler.
     *
     * 因此，在性能敏感的地方，在检查 coder 字段之前首先进行静态布尔 COMPACT_STRINGS 的显式检查，
     * 因为静态布尔 COMPACT_STRINGS 将由优化 JIT 编译器压缩。
     *
     *
     * The idioms for these cases are as follows.
     * 这些案例的惯用语法如下
     *
     *
     * For code such as:
     * 对于像这样的代码
     *
     *    if (coder == LATIN1) { ... }
     *
     * can be written more optimally as
     * 可以写得更优秀
     *
     *    if (coder() == LATIN1) { ... }
     *
     * or:
     *
     *    if (COMPACT_STRINGS && coder == LATIN1) { ... }
     *
     * An optimizing JIT compiler can fold the above conditional as:
     * 优化的JIT编译器可以将上述条件折叠为
     *
     *    COMPACT_STRINGS == true  => if (coder == LATIN1) { ... }
     *    COMPACT_STRINGS == false => if (false)           { ... }
     *
     * @implNote
     * The actual value for this field is injected by JVM.
     * 该字段的实际值由JVM注入。
     *
     * The static initialization block is used to set the value here to communicate
     * that this static final field is not statically foldable, and to
     * avoid any possible circular dependency during vm initialization.
     *
     * 静态初始化块用于设置此处的值以传达此静态常量字段不是静态可折叠的，
     * 并且在 vm 初始化期间避免任何可能的循环依赖性
     *
     */
    static final boolean COMPACT_STRINGS;

    static {
        COMPACT_STRINGS = true;
    }

    /**
     * Class String is special cased within the Serialization Stream Protocol.
     *
     * 字符串类是序列化流协议中的特殊字符串。
     *
     *
     * A String instance is written into an ObjectOutputStream according to
     * <a href="{@docRoot}/../specs/serialization/protocol.html#stream-elements">
     * Object Serialization Specification, Section 6.2, "Stream Elements"</a>
     *
     * String 实例根据 《对象序列化规范，第6.2节“流元素”》 写入 ObjectOutputStream
     *
     */
    private static final ObjectStreamField[] serialPersistentFields =
        new ObjectStreamField[0];

    /**
     * Initializes a newly created {@code String} object so that it represents
     * an empty character sequence.  Note that use of this constructor is
     * unnecessary since Strings are immutable.
     *
     * 初始化新创建的 String 对象，使其表示空字符序列。 请注意，由于字符串是不可变的，因此不必使用此构造函数。
     *
     */
    public String() {
        this.value = "".value;
        this.coder = "".coder;
    }

    /**
     * Initializes a newly created {@code String} object so that it represents
     * the same sequence of characters as the argument; in other words, the
     * newly created string is a copy of the argument string. Unless an
     * explicit copy of {@code original} is needed, use of this constructor is
     * unnecessary since Strings are immutable.
     *
     * 初始化新创建的 String 对象，使其表示与参数相同的字符序列; 换句话说，新创建的字符串是参数字符串的副本。
     * 除非需要 original 的明确副本，否则不必使用此构造函数，因为字符串是不可变的。
     *
     * @param  original A {@code String}
     *
     * @ HotSpotIntrinsicCandidate 它表示注释的方法可能（但不保证）由HotSpot VM内在化。
     * 如果HotSpot VM使用手写汇编和/或手写编译器IR（编译器内部函数）替换带注释的方法来提高性能，
     * 则该方法是固有的。
     */
    @HotSpotIntrinsicCandidate
    public String(String original) {
        this.value = original.value;
        this.coder = original.coder;
        this.hash = original.hash;
    }

    /**
     * Allocates a new {@code String} so that it represents the sequence of
     * characters currently contained in the character array argument.
     * 分配新的 String ，使其表示当前包含在字符数组参数中的字符序列。
     *
     * The contents of the character array are copied; subsequent modification of
     * the character array does not affect the newly created string.
     * 复制字符数组的内容到新分配的字符串中; 后续修改原字符数组不会影响新创建的字符串。
     *
     * @param  value  The initial value of the string 字符串的初始值
     */
    public String(char value[]) {
        this(value, 0, value.length, null);
    }

    /**
     * Allocates a new {@code String} that contains characters from a subarray
     * of the character array argument. The {@code offset} argument is the
     * index of the first character of the subarray and the {@code count}
     * argument specifies the length of the subarray. The contents of the
     * subarray are copied; subsequent modification of the character array does
     * not affect the newly created string.
     *
     * 分配一个新的 String ，其中包含字符数组参数的子数组中的字符。
     * offset 参数是子数组的第一个字符的索引， count 参数指定子数组的长度。
     * 子数组的内容被复制; 后续修改原字符数组不会影响新创建的字符串。
     *
     * @param  value
     *         Array that is the source of characters
     *         作为字符来源的数组
     *
     * @param  offset
     *         The initial offset
     *         初始偏移量
     *
     * @param  count
     *         The length
     *         长度
     *
     * @throws  IndexOutOfBoundsException  索引超出界限异常
     *          If {@code offset} is negative, {@code count} is negative, or
     *          {@code offset} is greater than {@code value.length - count}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     */
    public String(char value[], int offset, int count) {
        this(value, offset, count, rangeCheck(value, offset, count));
    }

    /**
     * 范围检查
     */
    private static Void rangeCheck(char[] value, int offset, int count) {
        checkBoundsOffCount(offset, count, value.length);
        return null;
    }

    /**
     * Allocates a new {@code String} that contains characters from a subarray
     * of the <a href="Character.html#unicode">Unicode code point</a> array
     * argument.  The {@code offset} argument is the index of the first code
     * point of the subarray and the {@code count} argument specifies the
     * length of the subarray.  The contents of the subarray are converted to
     * {@code char}s; subsequent modification of the {@code int} array does not
     * affect the newly created string.
     *
     * 分配一个新的 String ，其中包含 Unicode代码点 数组参数的子数组中的字符。
     * offset 参数是子数组的第一个代码点的索引， count 参数指定子数组的长度。
     * 子数组的内容转换为 char s; 后续修改原 int[] 数组不会影响新创建的字符串。
     *
     *
     * @param  codePoints
     *         Array that is the source of Unicode code points
     *         作为 Unicode 编码源的数组
     *
     * @param  offset
     *         The initial offset
     *         初始偏移量
     *
     * @param  count
     *         The length
     *         长度
     *
     * @throws  IllegalArgumentException   非法参数异常
     *          If any invalid Unicode code point is found in {@code
     *          codePoints}
     *          如果在 codePoints 中找到任何无效的 Unicode 编码
     *
     * @throws  IndexOutOfBoundsException 索引超出界限异常
     *          If {@code offset} is negative, {@code count} is negative, or
     *          {@code offset} is greater than {@code codePoints.length - count}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     *
     * @since  1.5
     */
    public String(int[] codePoints, int offset, int count) {
        checkBoundsOffCount(offset, count, codePoints.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS) {
            byte[] val = StringLatin1.toBytes(codePoints, offset, count);
            if (val != null) {
                this.coder = LATIN1;
                this.value = val;
                return;
            }
        }
        this.coder = UTF16;
        this.value = StringUTF16.toBytes(codePoints, offset, count);
    }

    /**
     * Allocates a new {@code String} constructed from a subarray of an array
     * of 8-bit integer values.
     *
     * 分配一个由 8 位整数值数组的子数组构成的新 String 。
     *
     *
     * <p> The {@code offset} argument is the index of the first byte of the
     * subarray, and the {@code count} argument specifies the length of the
     * subarray.
     *
     *  offset 参数是子数组的第一个字节的索引， count 参数指定子数组的长度。
     *
     *
     * <p> Each {@code byte} in the subarray is converted to a {@code char} as
     * specified in the {@link #String(byte[],int) String(byte[],int)} constructor.
     *
     * 子数组中的每个 byte 都转换为 char ，如 String（byte []，int）String（byte []，int） 构造函数中所指定。
     *
     * @deprecated This method does not properly convert bytes into characters.
     * As of JDK&nbsp;1.1, the preferred way to do this is via the
     * {@code String} constructors that take a {@link
     * java.nio.charset.Charset}, charset name, or that use the platform's
     * default charset.
     *
     * 此方法无法将字节正确转换为字符。 从JDK 1.1开始，
     * 执行此操作的首选方法是使用 java.nio.charset.Charset ，
     * charset名称 或 使用平台默认字符集的  String 构造函数。
     *
     * @param  ascii
     *         The bytes to be converted to characters
     *         要转换为字符的字节
     *
     * @param  hibyte
     *         The top 8 bits of each 16-bit Unicode code unit
     *         每个16位Unicode代码单元的前8位
     *
     *
     * @param  offset
     *         The initial offset
     *         初始偏移量
     *
     * @param  count
     *         The length
     *         长度
     *
     * @throws  IndexOutOfBoundsException 索引超出界限异常
     *          If {@code offset} is negative, {@code count} is negative, or
     *          {@code offset} is greater than {@code ascii.length - count}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     *
     * @see  #String(byte[], int)
     * @see  #String(byte[], int, int, java.lang.String)
     * @see  #String(byte[], int, int, java.nio.charset.Charset)
     * @see  #String(byte[], int, int)
     * @see  #String(byte[], java.lang.String)
     * @see  #String(byte[], java.nio.charset.Charset)
     * @see  #String(byte[])
     */
    @Deprecated(since="1.1")
    public String(byte ascii[], int hibyte, int offset, int count) {
        checkBoundsOffCount(offset, count, ascii.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS && (byte)hibyte == 0) {
            this.value = Arrays.copyOfRange(ascii, offset, offset + count);
            this.coder = LATIN1;
        } else {
            hibyte <<= 8;
            byte[] val = StringUTF16.newBytesFor(count);
            for (int i = 0; i < count; i++) {
                StringUTF16.putChar(val, i, hibyte | (ascii[offset++] & 0xff));
            }
            this.value = val;
            this.coder = UTF16;
        }
    }

    /**
     * Allocates a new {@code String} containing characters constructed from
     * an array of 8-bit integer values. Each character <i>c</i> in the
     * resulting string is constructed from the corresponding component
     * <i>b</i> in the byte array such that:
     *
     * 分配一个新的 String ，其中包含由8位整数值数组构成的字符。
     * 结果字符串中的每个字符  c  都是从字节数组中的相应组件 b  构造的，这样：
     *
     * <blockquote><pre>
     *     <b><i>c</i></b> == (char)(((hibyte &amp; 0xff) &lt;&lt; 8)
     *                         | (<b><i>b</i></b> &amp; 0xff))
     * </pre></blockquote>
     *
     * @deprecated  This method does not properly convert bytes into
     * characters.  As of JDK&nbsp;1.1, the preferred way to do this is via the
     * {@code String} constructors that take a {@link
     * java.nio.charset.Charset}, charset name, or that use the platform's
     * default charset.
     *
     * 此方法无法将字节正确转换为字符。
     * 从JDK 1.1开始，执行此操作的首选方法是使用 java.nio.charset.Charset ，
     * charset 名称或使用平台默认字符集的 String 构造函数。
     *
     * @param  ascii
     *         The bytes to be converted to characters
     *         要转换为字符的字节
     *
     * @param  hibyte
     *         The top 8 bits of each 16-bit Unicode code unit
     *         每个16位Unicode代码单元的前8位
     *
     * @see  #String(byte[], int, int, java.lang.String)
     * @see  #String(byte[], int, int, java.nio.charset.Charset)
     * @see  #String(byte[], int, int)
     * @see  #String(byte[], java.lang.String)
     * @see  #String(byte[], java.nio.charset.Charset)
     * @see  #String(byte[])
     */
    @Deprecated(since="1.1")
    public String(byte ascii[], int hibyte) {
        this(ascii, hibyte, 0, ascii.length);
    }

    /**
     * Constructs a new {@code String} by decoding the specified subarray of
     * bytes using the specified charset.  The length of the new {@code String}
     * is a function of the charset, and hence may not be equal to the length
     * of the subarray.
     *
     * 通过使用指定的字符集解码指定的字节子数组构造一个新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于子数组的长度。
     *
     * <p> The behavior of this constructor when the given bytes are not valid
     * in the given charset is unspecified.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 当给定字节在给定字符集中无效时，此构造函数的行为未指定。
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *         要解码为字符的字节
     *
     * @param  offset
     *         The index of the first byte to decode
     *         要解码的第一个字节的索引
     *
     * @param  length
     *         The number of bytes to decode
     *         要解码的字节数

     * @param  charsetName
     *         The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     *         受支持的 java.nio.charset.Charset charset 的名称
     *
     * @throws  UnsupportedEncodingException 不受支持的编码异常
     *          If the named charset is not supported
     *          如果不支持指定的charset
     *
     * @throws  IndexOutOfBoundsException  索引超出界限异常
     *          If {@code offset} is negative, {@code length} is negative, or
     *          {@code offset} is greater than {@code bytes.length - length}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     *
     * @since  1.1
     */
    public String(byte bytes[], int offset, int length, String charsetName)
            throws UnsupportedEncodingException {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret =
            StringCoding.decode(charsetName, bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    /**
     * Constructs a new {@code String} by decoding the specified subarray of
     * bytes using the specified {@linkplain java.nio.charset.Charset charset}.
     * The length of the new {@code String} is a function of the charset, and
     * hence may not be equal to the length of the subarray.
     *
     * 通过使用指定的 java.nio.charset.Charset charset 解码指定的字节子数组构造一个新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于子数组的长度。
     *
     * <p> This method always replaces malformed-input and unmappable-character
     * sequences with this charset's default replacement string.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 此方法始终使用此 charset 的默认替换字符串替换格式错误的输入和不可映射的字符序列。
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *
     *         要解码为字符的字节
     *
     * @param  offset
     *         The index of the first byte to decode
     *
     *         要解码的第一个字节的索引
     *
     * @param  length
     *         The number of bytes to decode
     *         要解码的字节数
     *
     * @param  charset
     *         The {@linkplain java.nio.charset.Charset charset} to be used to
     *         decode the {@code bytes}
     *
     *         java.nio.charset.Charset charset} 用于解码 bytes
     *
     * @throws  IndexOutOfBoundsException  索引超出界限异常
     *          If {@code offset} is negative, {@code length} is negative, or
     *          {@code offset} is greater than {@code bytes.length - length}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     *
     * @since  1.6
     */
    public String(byte bytes[], int offset, int length, Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret =
            StringCoding.decode(charset, bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    /**
     * Constructs a new {@code String} by decoding the specified array of bytes
     * using the specified {@linkplain java.nio.charset.Charset charset}.  The
     * length of the new {@code String} is a function of the charset, and hence
     * may not be equal to the length of the byte array.
     *
     * 通过使用指定的 java.nio.charset.Charset charset 解码指定的字节数组构造一个新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于字节数组的长度。
     *
     * <p> The behavior of this constructor when the given bytes are not valid
     * in the given charset is unspecified.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 当给定字节在给定字符集中无效时，此构造函数的行为未指定。
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *         要解码为字符的字节
     *
     * @param  charsetName
     *         The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     *         受支持的 java.nio.charset.Charset charset 的名称
     *
     * @throws  UnsupportedEncodingException 不受支持的编码异常
     *          If the named charset is not supported
     *          如果不支持指定的charset
     *
     * @since  1.1
     */
    public String(byte bytes[], String charsetName)
            throws UnsupportedEncodingException {
        this(bytes, 0, bytes.length, charsetName);
    }

    /**
     * Constructs a new {@code String} by decoding the specified array of
     * bytes using the specified {@linkplain java.nio.charset.Charset charset}.
     * The length of the new {@code String} is a function of the charset, and
     * hence may not be equal to the length of the byte array.
     *
     * 通过使用指定的 java.nio.charset.Charset charset 解码指定的字节数组构造一个新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于字节数组的长度。
     *
     * <p> This method always replaces malformed-input and unmappable-character
     * sequences with this charset's default replacement string.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 此方法始终使用此 charset 的默认替换字符串替换格式错误的输入和不可映射的字符序列。
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *         要解码为字符的字节
     *
     * @param  charset
     *         The {@linkplain java.nio.charset.Charset charset} to be used to
     *         decode the {@code bytes}
     *
     *         java.nio.charset.Charset charset 用于解码 bytes
     *
     * @since  1.6
     */
    public String(byte bytes[], Charset charset) {
        this(bytes, 0, bytes.length, charset);
    }

    /**
     * Constructs a new {@code String} by decoding the specified subarray of
     * bytes using the platform's default charset.  The length of the new
     * {@code String} is a function of the charset, and hence may not be equal
     * to the length of the subarray.
     *
     * 通过使用平台的默认字符集解码指定的字节子数组来构造新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于子数组的长度。
     *
     * <p> The behavior of this constructor when the given bytes are not valid
     * in the default charset is unspecified.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 未指定给定字节在默认字符集中无效时此构造函数的行为
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *         要解码为字符的字节
     *
     * @param  offset
     *         The index of the first byte to decode
     *         要解码的第一个字节的索引
     *
     * @param  length
     *         The number of bytes to decode
     *         要解码的字节数
     *
     * @throws  IndexOutOfBoundsException 索引超出界限异常
     *          If {@code offset} is negative, {@code length} is negative, or
     *          {@code offset} is greater than {@code bytes.length - length}
     *
     *          如果 offset 为负数，count 为负数，或 offset 大于 value.length  -  count
     *
     * @since  1.1
     */
    public String(byte bytes[], int offset, int length) {
        checkBoundsOffCount(offset, length, bytes.length);
        StringCoding.Result ret = StringCoding.decode(bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    /**
     * Constructs a new {@code String} by decoding the specified array of bytes
     * using the platform's default charset.  The length of the new {@code
     * String} is a function of the charset, and hence may not be equal to the
     * length of the byte array.
     *
     * 通过使用平台的默认字符集解码指定的字节数组构造一个新的 String 。
     * 新的 String 的长度是字符集的函数，因此可能不等于字节数组的长度。
     *
     * <p> The behavior of this constructor when the given bytes are not valid
     * in the default charset is unspecified.  The {@link
     * java.nio.charset.CharsetDecoder} class should be used when more control
     * over the decoding process is required.
     *
     * 未指定给定字节在默认字符集中无效时此构造函数的行为。
     * 当需要对解码过程进行更多控制时，应使用 java.nio.charset.CharsetDecoder 类。
     *
     *
     * @param  bytes
     *         The bytes to be decoded into characters
     *         要解码为字符的字节
     *
     * @since  1.1
     */
    public String(byte[] bytes) {
        this(bytes, 0, bytes.length);
    }

    /**
     * Allocates a new string that contains the sequence of characters
     * currently contained in the string buffer argument. The contents of the
     * string buffer are copied; subsequent modification of the string buffer
     * does not affect the newly created string.
     *
     * 分配一个新字符串，其中包含当前包含在字符串缓冲区参数中的字符序列。
     * 复制字符串缓冲区的内容; 后续修改原字符串缓冲区不会影响新创建的字符串。
     *
     * @param  buffer
     *         A {@code StringBuffer}
     */
    public String(StringBuffer buffer) {
        this(buffer.toString());
    }

    /**
     * Allocates a new string that contains the sequence of characters
     * currently contained in the string builder argument. The contents of the
     * string builder are copied; subsequent modification of the string builder
     * does not affect the newly created string.
     *
     * 分配一个新字符串，其中包含当前包含在字符串构建器参数中的字符序列。
     * 复制字符串生成器的内容; 后续修改字符串生成器不会影响新创建的字符串。
     *
     *
     * <p> This constructor is provided to ease migration to {@code
     * StringBuilder}. Obtaining a string from a string builder via the {@code
     * toString} method is likely to run faster and is generally preferred.
     *
     * 提供此构造函数是为了便于迁移到 StringBuilder 。
     * 通过 toString 方法从字符串生成器获取字符串可能运行得更快，通常是首选。
     *
     * @param   builder
     *          A {@code StringBuilder}
     *
     * @since  1.5
     */
    public String(StringBuilder builder) {
        this(builder, null);
    }

    /**
     * Returns the length of this string.
     * 返回此字符串的长度。
     * The length is equal to the number of <a href="Character.html#unicode">Unicode
     * code units</a> in the string.
     * 长度等于字符串中 Unicode 代码单元 的数量。
     *
     * @return  the length of the sequence of characters represented by this
     *          object.
     *          此对象表示的字符序列的长度。
     */
    public int length() {
        return value.length >> coder();
    }

    /**
     * Returns {@code true} if, and only if, {@link #length()} is {@code 0}.
     * 当且仅当 length（）为 0 时返回true。
     *
     * @return {@code true} if {@link #length()} is {@code 0}, otherwise
     * {@code false}
     *
     * 如果length（）为0，则为 true，否则为 false
     *
     * @since 1.6
     */
    public boolean isEmpty() {
        return value.length == 0;
    }

    /**
     * Returns the {@code char} value at the
     * specified index. An index ranges from {@code 0} to
     * {@code length() - 1}. The first {@code char} value of the sequence
     * is at index {@code 0}, the next at index {@code 1},
     * and so on, as for array indexing.
     *
     * 返回指定索引处的 char 值。 索引范围从 0 到 length（） -  1 。
     * 序列的第一个 char 值位于索引 0 ，下一个位于索引 1 ，依此类推，就像数组索引一样。
     *
     * <p>If the {@code char} value specified by the index is a
     * <a href="Character.html#unicode">surrogate</a>, the surrogate
     * value is returned.
     *
     * 如果索引指定的 char 值是 代理 ，则返回代理值。
     *
     * @param      index   the index of the {@code char} value.
     *              char 值的索引。
     *
     * @return     the {@code char} value at the specified index of this string.
     *             The first {@code char} value is at index {@code 0}.
     *
     *             此字符串的指定索引处的{@code char}值。
     *             第一个{@code char}值位于索引{@code 0}。
     *
     *
     * @exception  IndexOutOfBoundsException  if the {@code index}
     *             argument is negative or not less than the length of this
     *             string.
     *
     *             如果 index 参数为负数或不小于此字符串的长度。
     */
    public char charAt(int index) {
        if (isLatin1()) {
            return StringLatin1.charAt(value, index);
        } else {
            return StringUTF16.charAt(value, index);
        }
    }

    /**
     * Returns the character (Unicode code point) at the specified
     * index. The index refers to {@code char} values
     * (Unicode code units) and ranges from {@code 0} to
     * {@link #length()}{@code  - 1}.
     *
     * 返回指定索引处的字符（Unicode 编码点）。
     * 索引引用 char 值（Unicode 编码单元），
     * 范围从 0 到 length（） -  1 。
     *
     * <p> If the {@code char} value specified at the given index
     * is in the high-surrogate range, the following index is less
     * than the length of this {@code String}, and the
     * {@code char} value at the following index is in the
     * low-surrogate range, then the supplementary code point
     * corresponding to this surrogate pair is returned. Otherwise,
     * the {@code char} value at the given index is returned.
     *
     * 如果在给定索引处指定的 char 值在高代理范围内，
     * 则以下索引小于此 String 的长度，并且以下索引处的 char 值为在低代理范围内，
     * 然后返回与该代理对相对应的补充代码点。
     * 否则，返回给定索引处的 char 值。
     *
     * @param      index the index to the {@code char} values
     *                    char 值的索引
     *
     * @return     the code point value of the character at the
     *             {@code index}
     *
     *              index 中字符的代码点值
     *
     * @exception  IndexOutOfBoundsException  if the {@code index}
     *             argument is negative or not less than the length of this
     *             string.
     *
     *             如果 index 参数为负数或不小于此字符串的长度。
     *
     * @since      1.5
     */
    public int codePointAt(int index) {
        if (isLatin1()) {
            checkIndex(index, value.length);
            return value[index] & 0xff;
        }
        int length = value.length >> 1;
        checkIndex(index, length);
        return StringUTF16.codePointAt(value, index, length);
    }

    /**
     * Returns the character (Unicode code point) before the specified
     * index. The index refers to {@code char} values
     * (Unicode code units) and ranges from {@code 1} to {@link
     * CharSequence#length() length}.
     *
     * 返回指定索引之前的字符（Unicode代码点）。
     * 索引引用 char 值（Unicode代码单元），
     * 范围从 1 到 length（）
     *
     *
     * <p> If the {@code char} value at {@code (index - 1)}
     * is in the low-surrogate range, {@code (index - 2)} is not
     * negative, and the {@code char} value at {@code (index -
     * 2)} is in the high-surrogate range, then the
     * supplementary code point value of the surrogate pair is
     * returned. If the {@code char} value at {@code index -
     * 1} is an unpaired low-surrogate or a high-surrogate, the
     * surrogate value is returned.
     *
     * 如果 （index - 1) 上的 char 值在低代理范围内，则 （index  -  2） 不为负数，
     * char 值为 （index  -  2） 处于高代理范围内， 然后返回代理对的补充代码点值。
     * 如果 index  -  1 处的 char 值是未配对的低代理或高代理，则返回代理值。
     *
     * @param     index the index following the code point that should be returned
     *
     *                   应该返回的代码点后面的索引
     *
     * @return    the Unicode code point value before the given index.
     *
     *             给定索引之前的 Unicode 代码点值。
     *
     * @exception IndexOutOfBoundsException if the {@code index}
     *            argument is less than 1 or greater than the length
     *            of this string.
     *
     *            如果 index 参数小于1或大于此字符串的长度。
     * @since     1.5
     */
    public int codePointBefore(int index) {
        int i = index - 1;
        if (i < 0 || i >= length()) {
            throw new StringIndexOutOfBoundsException(index);
        }
        if (isLatin1()) {
            return (value[i] & 0xff);
        }
        return StringUTF16.codePointBefore(value, index);
    }

    /**
     * Returns the number of Unicode code points in the specified text
     * range of this {@code String}. The text range begins at the
     * specified {@code beginIndex} and extends to the
     * {@code char} at index {@code endIndex - 1}. Thus the
     * length (in {@code char}s) of the text range is
     * {@code endIndex-beginIndex}. Unpaired surrogates within
     * the text range count as one code point each.
     *
     * 返回此 String 指定文本范围内的 Unicode 编码 。
     * 文本范围从指定的 beginIndex 开始，并在索引 endIndex - 1 处扩展到 char 。
     * 因此，文本范围的长度（在 char 中）是 endIndex - beginIndex 。
     * 文本范围内的未配对代理计为每个代码点。
     *
     * @param beginIndex the index to the first {@code char} of
     * the text range.
     *                文本范围的第一个 char 的索引。
     *
     * @param endIndex the index after the last {@code char} of
     * the text range.
     *                 文本范围的最后一个 char 之后的索引。
     *
     * @return the number of Unicode code points in the specified text
     * range
     *
     *  指定文本范围内的 Unicode 代码点数
     *
     * @exception IndexOutOfBoundsException if the
     * {@code beginIndex} is negative, or {@code endIndex}
     * is larger than the length of this {@code String}, or
     * {@code beginIndex} is larger than {@code endIndex}.
     *
     * 如果 beginIndex 为负数，或者 endIndex 大于此 String 的长度，或者 beginIndex 大于 endIndex 。
     *
     * @since  1.5
     */
    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex < 0 || beginIndex > endIndex ||
            endIndex > length()) {
            throw new IndexOutOfBoundsException();
        }
        if (isLatin1()) {
            return endIndex - beginIndex;
        }
        return StringUTF16.codePointCount(value, beginIndex, endIndex);
    }

    /**
     * Returns the index within this {@code String} that is
     * offset from the given {@code index} by
     * {@code codePointOffset} code points. Unpaired surrogates
     * within the text range given by {@code index} and
     * {@code codePointOffset} count as one code point each.
     *
     * @param index the index to be offset
     * @param codePointOffset the offset in code points
     * @return the index within this {@code String}
     * @exception IndexOutOfBoundsException if {@code index}
     *   is negative or larger then the length of this
     *   {@code String}, or if {@code codePointOffset} is positive
     *   and the substring starting with {@code index} has fewer
     *   than {@code codePointOffset} code points,
     *   or if {@code codePointOffset} is negative and the substring
     *   before {@code index} has fewer than the absolute value
     *   of {@code codePointOffset} code points.
     * @since 1.5
     */
    public int offsetByCodePoints(int index, int codePointOffset) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException();
        }
        return Character.offsetByCodePoints(this, index, codePointOffset);
    }

    /**
     * Copies characters from this string into the destination character
     * array.
     *
     * 将此字符串中的字符复制到目标字符数组中。
     *
     * <p>
     * The first character to be copied is at index {@code srcBegin};
     * the last character to be copied is at index {@code srcEnd-1}
     * (thus the total number of characters to be copied is
     * {@code srcEnd-srcBegin}). The characters are copied into the
     * subarray of {@code dst} starting at index {@code dstBegin}
     * and ending at index:
     *
     * 要复制的第一个字符是索引 srcBegin ; 要复制的最后一个字符位于索引 srcEnd-1
     * （因此要复制的字符总数为 srcEnd-srcBegin 。 字符被复制到 dst 的子数组中，
     * 从索引 dstBegin 开始并以索引结束：
     *
     * <blockquote><pre>
     *     dstBegin + (srcEnd-srcBegin) - 1
     * </pre></blockquote>
     *
     * @param      srcBegin   index of the first character in the string
     *                        to copy.
     *                        要复制的字符串中第一个字符的索引。
     *
     * @param      srcEnd     index after the last character in the string
     *                        to copy.
     *                        要复制的字符串中的最后一个字符后的索引。
     *
     * @param      dst        the destination array.
     *                        目标数组。
     *
     * @param      dstBegin   the start offset in the destination array.
     *                        目标数组中的起始偏移量。
     *
     * @exception IndexOutOfBoundsException If any of the following
     *            is true:
     *            如果满足以下任何条件则抛出 索引超出界限异常：
     *
     *            <ul><li>{@code srcBegin} is negative.
     *            <li>{@code srcBegin} is greater than {@code srcEnd}
     *            <li>{@code srcEnd} is greater than the length of this
     *                string
     *            <li>{@code dstBegin} is negative
     *            <li>{@code dstBegin+(srcEnd-srcBegin)} is larger than
     *                {@code dst.length}</ul>
     */
    public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) {
        checkBoundsBeginEnd(srcBegin, srcEnd, length());
        checkBoundsOffCount(dstBegin, srcEnd - srcBegin, dst.length);
        if (isLatin1()) {
            StringLatin1.getChars(value, srcBegin, srcEnd, dst, dstBegin);
        } else {
            StringUTF16.getChars(value, srcBegin, srcEnd, dst, dstBegin);
        }
    }

    /**
     * Copies characters from this string into the destination byte array. Each
     * byte receives the 8 low-order bits of the corresponding character. The
     * eight high-order bits of each character are not copied and do not
     * participate in the transfer in any way.
     *
     * 将此字符串中的字符复制到目标字节数组中。
     * 每个字节接收相应字符的8个低位。
     * 每个字符的八个高位不会被复制，也不会以任何方式参与传输。
     *
     * <p> The first character to be copied is at index {@code srcBegin}; the
     * last character to be copied is at index {@code srcEnd-1}.  The total
     * number of characters to be copied is {@code srcEnd-srcBegin}. The
     * characters, converted to bytes, are copied into the subarray of {@code
     * dst} starting at index {@code dstBegin} and ending at index:
     *
     * 要复制的第一个字符是索引 srcBegin ; 要复制的最后一个字符是索引 srcEnd-1 。
     * 要复制的字符总数为 srcEnd-srcBegin 。 转换为字节的字符将被复制到 dst 的子数组中，
     * 从索引 dstBegin 开始并以索引结束：
     *
     * <blockquote><pre>
     *     dstBegin + (srcEnd-srcBegin) - 1
     * </pre></blockquote>
     *
     * @deprecated  This method does not properly convert characters into
     * bytes.  As of JDK&nbsp;1.1, the preferred way to do this is via the
     * {@link #getBytes()} method, which uses the platform's default charset.
     *
     * 此方法无法将字符正确转换为字节。 从JDK 1.1开始，
     * 执行此操作的首选方法是使用 getBytes（） 方法，该方法使用平台的默认字符集。
     *
     * @param  srcBegin
     *         Index of the first character in the string to copy
     *         要复制的字符串中第一个字符的索引
     *
     * @param  srcEnd
     *         Index after the last character in the string to copy
     *         要复制的字符串中的最后一个字符后的索引
     *
     * @param  dst
     *         The destination array
     *         目标数组
     *
     * @param  dstBegin
     *         The start offset in the destination array
     *         目标数组中的起始偏移量
     *
     * @throws  IndexOutOfBoundsException
     *          If any of the following is true:
     *          <ul>
     *            <li> {@code srcBegin} is negative
     *            <li> {@code srcBegin} is greater than {@code srcEnd}
     *            <li> {@code srcEnd} is greater than the length of this String
     *            <li> {@code dstBegin} is negative
     *            <li> {@code dstBegin+(srcEnd-srcBegin)} is larger than {@code
     *                 dst.length}
     *          </ul>
     */
    @Deprecated(since="1.1")
    public void getBytes(int srcBegin, int srcEnd, byte dst[], int dstBegin) {
        checkBoundsBeginEnd(srcBegin, srcEnd, length());
        Objects.requireNonNull(dst);
        checkBoundsOffCount(dstBegin, srcEnd - srcBegin, dst.length);
        if (isLatin1()) {
            StringLatin1.getBytes(value, srcBegin, srcEnd, dst, dstBegin);
        } else {
            StringUTF16.getBytes(value, srcBegin, srcEnd, dst, dstBegin);
        }
    }

    /**
     * Encodes this {@code String} into a sequence of bytes using the named
     * charset, storing the result into a new byte array.
     *
     * 使用命名的字符集将此 String 编码为字节序列，将结果存储到新的字节数组中。
     *
     * <p> The behavior of this method when this string cannot be encoded in
     * the given charset is unspecified.  The {@link
     * java.nio.charset.CharsetEncoder} class should be used when more control
     * over the encoding process is required.
     *
     * 未指定此字符串无法在给定字符集中进行编码时此方法的行为。
     * 当需要对编码过程进行更多控制时，应使用 java.nio.charset.CharsetEncoder 类。
     *
     * @param  charsetName
     *         The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     *
     *         受支持的 java.nio.charset.Charset charset 的名称
     *
     * @return  The resultant byte array
     *
     *           结果字节数组
     *
     * @throws  UnsupportedEncodingException  不受支持的编码异常
     *          If the named charset is not supported
     *
     *          如果不支持指定的charset
     *
     * @since  1.1
     */
    public byte[] getBytes(String charsetName)
            throws UnsupportedEncodingException {
        if (charsetName == null) throw new NullPointerException();
        return StringCoding.encode(charsetName, coder(), value);
    }

    /**
     * Encodes this {@code String} into a sequence of bytes using the given
     * {@linkplain java.nio.charset.Charset charset}, storing the result into a
     * new byte array.
     *
     * 使用给定的 java.nio.charset.Charset charset 将此 String 编码为字节序列，
     * 将结果存储到新的字节数组中。
     *
     * <p> This method always replaces malformed-input and unmappable-character
     * sequences with this charset's default replacement byte array.  The
     * {@link java.nio.charset.CharsetEncoder} class should be used when more
     * control over the encoding process is required.
     *
     * 此方法始终使用此 charset 的缺省替换字节数组替换格式错误的输入和不可映射的字符序列。
     * 当需要对编码过程进行更多控制时，应使用 java.nio.charset.CharsetEncoder 类。
     *
     * @param  charset
     *         The {@linkplain java.nio.charset.Charset} to be used to encode
     *         the {@code String}
     *
     *         用于编码 String 的 java.nio.charset.Charset
     *
     * @return  The resultant byte array
     *           结果字节数组
     *
     * @since  1.6
     */
    public byte[] getBytes(Charset charset) {
        if (charset == null) throw new NullPointerException();
        return StringCoding.encode(charset, coder(), value);
     }

    /**
     * Encodes this {@code String} into a sequence of bytes using the
     * platform's default charset, storing the result into a new byte array.
     *
     * 使用平台的默认字符集将此 String 编码为字节序列，将结果存储到新的字节数组中。
     *
     * <p> The behavior of this method when this string cannot be encoded in
     * the default charset is unspecified.  The {@link
     * java.nio.charset.CharsetEncoder} class should be used when more control
     * over the encoding process is required.
     *
     * 未指定此字符串无法在默认字符集进行编码时此方法的行为。
     * 当需要对编码过程进行更多控制时，应使用 java.nio.charset.CharsetEncoder 类。
     *
     * @return  The resultant byte array
     *           结果字节数组
     *
     * @since      1.1
     */
    public byte[] getBytes() {
        return StringCoding.encode(coder(), value);
    }

    /**
     * Compares this string to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * String} object that represents the same sequence of characters as this
     * object.
     * 将此字符串与指定的对象进行比较。结果是 true 当且仅当参数不是 null 且是 String 对象时，
     * 它表示与此对象有相同的字符序列。
     *
     * <p>For finer-grained String comparison, refer to
     * {@link java.text.Collator}.
     *
     * 有关细粒度的字符串比较，请参阅 java.text.Collator 。
     *
     * @param  anObject
     *         The object to compare this {@code String} against
     *         要将此 String 与之进行比较的对象
     *
     * @return  {@code true} if the given object represents a {@code String}
     *          equivalent to this string, {@code false} otherwise
     *
     *          返回 true 如果给定对象表示与此字符串等效的 String ，则为 false
     *
     * @see  #compareTo(String)
     * @see  #equalsIgnoreCase(String)
     */
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String aString = (String)anObject;
            if (coder() == aString.coder()) {
                return isLatin1() ? StringLatin1.equals(value, aString.value)
                                  : StringUTF16.equals(value, aString.value);
            }
        }
        return false;
    }

    /**
     * Compares this string to the specified {@code StringBuffer}.  The result
     * is {@code true} if and only if this {@code String} represents the same
     * sequence of characters as the specified {@code StringBuffer}. This method
     * synchronizes on the {@code StringBuffer}.
     *
     * 将此字符串与指定的 StringBuffer 进行比较。
     * 当且仅当此 String 表示与指定的 StringBuffer 相同的字符序列时，
     * 结果为 true 。 此方法在 StringBuffer 上同步。
     *
     * <p>For finer-grained String comparison, refer to
     * {@link java.text.Collator}.
     *
     * 有关细粒度的字符串比较，请参阅 java.text.Collator 。
     *
     * @param  sb
     *         The {@code StringBuffer} to compare this {@code String} against
     *
     *         StringBuffer 将此 String 与之进行比较
     *
     * @return  {@code true} if this {@code String} represents the same
     *          sequence of characters as the specified {@code StringBuffer},
     *          {@code false} otherwise
     *
     *          返回 true 如果此 String 表示与指定的 StringBuffer 相同的字符序列，
     *          否则为 false
     *
     * @since  1.4
     */
    public boolean contentEquals(StringBuffer sb) {
        return contentEquals((CharSequence)sb);
    }

    /**
     * 非同步内容比较
     */
    private boolean nonSyncContentEquals(AbstractStringBuilder sb) {
        int len = length();
        if (len != sb.length()) {
            return false;
        }
        byte v1[] = value;
        byte v2[] = sb.getValue();
        if (coder() == sb.getCoder()) {
            int n = v1.length;
            for (int i = 0; i < n; i++) {
                if (v1[i] != v2[i]) {
                    return false;
                }
            }
        } else {
            if (!isLatin1()) {  // utf16 str and latin1 abs can never be "equal"
                return false;
            }
            return StringUTF16.contentEquals(v1, v2, len);
        }
        return true;
    }

    /**
     * Compares this string to the specified {@code CharSequence}.  The
     * result is {@code true} if and only if this {@code String} represents the
     * same sequence of char values as the specified sequence. Note that if the
     * {@code CharSequence} is a {@code StringBuffer} then the method
     * synchronizes on it.
     *
     * 将此字符串与指定的 CharSequence 进行比较。
     * 当且仅当此 String 表示与指定序列相同的 char 值序列时，结果为 true 。
     * 请注意，如果 CharSequence 是 StringBuffer ，则该方法会在其上进行同步。
     *
     * <p>For finer-grained String comparison, refer to
     * {@link java.text.Collator}.
     *
     * 有关细粒度的字符串比较，请参阅 java.text.Collator 。
     *
     * @param  cs
     *         The sequence to compare this {@code String} against
     *         用于比较此 String 的序列
     *
     * @return  {@code true} if this {@code String} represents the same
     *          sequence of char values as the specified sequence, {@code
     *          false} otherwise
     *
     *          返回 true 如果此 String 表示与指定序列相同 char 值序列，否则 false
     *
     * @since  1.5
     */
    public boolean contentEquals(CharSequence cs) {
        // Argument is a StringBuffer, StringBuilder
        if (cs instanceof AbstractStringBuilder) {
            if (cs instanceof StringBuffer) {
                synchronized(cs) {
                   return nonSyncContentEquals((AbstractStringBuilder)cs);
                }
            } else {
                return nonSyncContentEquals((AbstractStringBuilder)cs);
            }
        }
        // Argument is a String
        if (cs instanceof String) {
            return equals(cs);
        }
        // Argument is a generic CharSequence
        int n = cs.length();
        if (n != length()) {
            return false;
        }
        byte[] val = this.value;
        if (isLatin1()) {
            for (int i = 0; i < n; i++) {
                if ((val[i] & 0xff) != cs.charAt(i)) {
                    return false;
                }
            }
        } else {
            if (!StringUTF16.contentEquals(val, cs, n)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares this {@code String} to another {@code String}, ignoring case
     * considerations.  Two strings are considered equal ignoring case if they
     * are of the same length and corresponding characters in the two strings
     * are equal ignoring case.
     *
     * 将此 String 与另一个 String 进行比较，忽略大小写事项。
     * 如果两个字符串具有相同的长度并且两个字符串中的相应字符等于忽略大小写，
     * 则认为两个字符串是相等的忽略大小写。
     *
     * <p> Two characters {@code c1} and {@code c2} are considered the same
     * ignoring case if at least one of the following is true:
     * 如果至少满足下列条件之一，则两个字符 c1 和 c2 被视为相同的忽略大小写：
     *
     * <ul>
     *   <li> The two characters are the same (as compared by the
     *        {@code ==} operator)
     *
     *        这两个字符是相同的（通过 == 运算符进行比较）
     *
     *   <li> Calling {@code Character.toLowerCase(Character.toUpperCase(char))}
     *        on each character produces the same result
     *
     *        在每个字符上调用 Character.toLowerCase（Character.toUpperCase（char）） 会产生相同的结果
     *
     * </ul>
     *
     * <p>Note that this method does <em>not</em> take locale into account, and
     * will result in unsatisfactory results for certain locales.  The
     * {@link java.text.Collator} class provides locale-sensitive comparison.
     *
     * 请注意，此方法<em>不</ em>将区域设置考虑在内，
     * 并且会导致某些区域设置的结果不令人满意。
     * java.text.Collator 类提供区分区域敏感的比较。
     *
     * @param  anotherString
     *         The {@code String} to compare this {@code String} against
     *
     *         String 将此 String 与之进行比较
     *
     * @return  {@code true} if the argument is not {@code null} and it
     *          represents an equivalent {@code String} ignoring case; {@code
     *          false} otherwise
     *
     *          返回 true 如果参数不是 null 并且它代表一个等价的 String 忽略的情况;
     *          否则为 false
     *
     * @see  #equals(Object)
     */
    public boolean equalsIgnoreCase(String anotherString) {
        return (this == anotherString) ? true
                : (anotherString != null)
                && (anotherString.length() == length())
                && regionMatches(true, 0, anotherString, 0, length());
    }

    /**
     * Compares two strings lexicographically.
     * 按字典顺序比较两个字符串。
     *
     * The comparison is based on the Unicode value of each character in
     * the strings. The character sequence represented by this
     * {@code String} object is compared lexicographically to the
     * character sequence represented by the argument string.
     *
     * 比较基于字符串中每个字符的Unicode值。
     * 此 String 对象表示的字符序列按字典顺序与参数字符串表示的字符序列进行比较。
     *
     * The result is a negative integer if this {@code String} object
     * lexicographically precedes the argument string. The result is a
     * positive integer if this {@code String} object lexicographically
     * follows the argument string. The result is zero if the strings
     * are equal; {@code compareTo} returns {@code 0} exactly when
     * the {@link #equals(Object)} method would return {@code true}.
     *
     * 如果此 String 对象按字典顺序排在参数字符串之前，则结果为负整数。
     * 如果此 String 对象按字典顺序排在参数字符串之后，则结果为正整数。
     * 如果字符串相等，结果为零;
     * compareTo 在 equals（Object） 方法返回 true 时完全返回 0 。
     *
     * <p>
     * This is the definition of lexicographic ordering. If two strings are
     * different, then either they have different characters at some index
     * that is a valid index for both strings, or their lengths are different,
     * or both.
     *
     * 这是词典排序的定义。 如果两个字符串不同，则它们在某个索引处具有不同的字符，
     * 这些字符串是两个字符串的有效索引，或者它们的长度不同，或者两者都有。
     *
     * If they have different characters at one or more index
     * positions, let <i>k</i> be the smallest such index; then the string
     * whose character at position <i>k</i> has the smaller value, as
     * determined by using the {@code <} operator, lexicographically precedes the
     * other string. In this case, {@code compareTo} returns the
     * difference of the two character values at position {@code k} in
     * the two string -- that is, the value:
     *
     * 如果它们在一个或多个索引位置具有不同的字符，则让 k 成为最小的索引;
     * 然后是位于 k  的字符具有较小值的字符串，
     * 通过使用 < 算符确定，按字典顺序排在另一个字符串之前。
     * 在这种情况下， compareTo 返回两个字符串中位置  k 处两个字符值的差异 - 即值是：
     *
     * this.charAt(k)-anotherString.charAt(k)
     *
     *
     * <blockquote><pre>
     * this.charAt(k)-anotherString.charAt(k)
     * </pre></blockquote>
     *
     * If there is no index position at which they differ, then the shorter
     * string lexicographically precedes the longer string. In this case,
     * {@code compareTo} returns the difference of the lengths of the
     * strings -- that is, the value:
     *
     * 如果没有它们不同的索引位置，那么较短的字符串按字典顺序排在较长的字符串之前。
     * 在这种情况下， compareTo 返回字符串长度的差异 - 即值是：
     *
     * this.length()-anotherString.length()
     *
     * <blockquote><pre>
     * this.length()-anotherString.length()
     * </pre></blockquote>
     *
     * <p>For finer-grained String comparison, refer to
     * {@link java.text.Collator}.
     *
     * 有关细粒度的字符串比较，请参阅 java.text.Collator 。
     *
     * @param   anotherString   the {@code String} to be compared.
     *                          要比较的 String 。
     *
     * @return  the value {@code 0} if the argument string is equal to
     *          this string; a value less than {@code 0} if this string
     *          is lexicographically less than the string argument; and a
     *          value greater than {@code 0} if this string is
     *          lexicographically greater than the string argument.
     *
     *          如果参数字符串等于此字符串，则值为 0 ;
     *          如果此字符串按字典顺序小于字符串参数，则值小于 0 ;
     *          如果此字符串按字典顺序大于字符串参数，则值大于 0 。
     */
    public int compareTo(String anotherString) {
        byte v1[] = value;
        byte v2[] = anotherString.value;
        if (coder() == anotherString.coder()) {
            return isLatin1() ? StringLatin1.compareTo(v1, v2)
                              : StringUTF16.compareTo(v1, v2);
        }
        return isLatin1() ? StringLatin1.compareToUTF16(v1, v2)
                          : StringUTF16.compareToLatin1(v1, v2);
     }

    /**
     * A Comparator that orders {@code String} objects as by
     * {@code compareToIgnoreCase}. This comparator is serializable.
     *
     * 由 compareToIgnoreCase 命令 String 对象的比较器。 该比较器是可序列化的。
     *
     * <p>
     * Note that this Comparator does <em>not</em> take locale into account,
     * and will result in an unsatisfactory ordering for certain locales.
     * The {@link java.text.Collator} class provides locale-sensitive comparison.
     *
     * 请注意，此比较器 不 将区域设置考虑在内，
     * 并且会导致某些区域设置的排序不令人满意。
     * java.text.Collator 类提供区分区域敏感的比较。
     *
     * @see     java.text.Collator
     * @since   1.2
     */
    public static final Comparator<String> CASE_INSENSITIVE_ORDER
                                         = new CaseInsensitiveComparator();
    private static class CaseInsensitiveComparator
            implements Comparator<String>, java.io.Serializable {
        // use serialVersionUID from JDK 1.2.2 for interoperability
        private static final long serialVersionUID = 8575799808933029326L;

        public int compare(String s1, String s2) {
            byte v1[] = s1.value;
            byte v2[] = s2.value;
            if (s1.coder() == s2.coder()) {
                return s1.isLatin1() ? StringLatin1.compareToCI(v1, v2)
                                     : StringUTF16.compareToCI(v1, v2);
            }
            return s1.isLatin1() ? StringLatin1.compareToCI_UTF16(v1, v2)
                                 : StringUTF16.compareToCI_Latin1(v1, v2);
        }

        /** Replaces the de-serialized object. 替换反序列化的对象。   */
        private Object readResolve() { return CASE_INSENSITIVE_ORDER; }
    }

    /**
     * Compares two strings lexicographically, ignoring case
     * differences. This method returns an integer whose sign is that of
     * calling {@code compareTo} with normalized versions of the strings
     * where case differences have been eliminated by calling
     * {@code Character.toLowerCase(Character.toUpperCase(character))} on
     * each character.
     *
     * 按字典顺序比较两个字符串，忽略大小写差异。
     * 此方法返回一个整数，其符号是调用  compareTo 的符号，
     * 其中字符串的规范化版本通过在每个字符上调用
     * Character.toLowerCase（Character.toUpperCase（character）） 来消除大小写差异。
     *
     * <p>
     * Note that this method does <em>not</em> take locale into account,
     * and will result in an unsatisfactory ordering for certain locales.
     * The {@link java.text.Collator} class provides locale-sensitive comparison.
     *
     * 请注意，此方法 不 将区域设置考虑在内，
     * 并且会导致某些区域设置的排序不令人满意。
     * java.text.Collator 类提供区分区域敏感的比较。
     *
     * @param   str   the {@code String} to be compared.
     *                要比较的 String 。
     * @return  a negative integer, zero, or a positive integer as the
     *          specified String is greater than, equal to, or less
     *          than this String, ignoring case considerations.
     *
     *          指定String大于，等于或小于此String的负整数，零或正整数，忽略大小写考虑因素。
     *
     * @see     java.text.Collator
     * @since   1.2
     */
    public int compareToIgnoreCase(String str) {
        return CASE_INSENSITIVE_ORDER.compare(this, str);
    }

    /**
     * Tests if two string regions are equal.
     * 测试两个字符串区域是否相等。
     *
     * <p>
     * A substring of this {@code String} object is compared to a substring
     * of the argument other. The result is true if these substrings
     * represent identical character sequences. The substring of this
     * {@code String} object to be compared begins at index {@code toffset}
     * and has length {@code len}. The substring of other to be compared
     * begins at index {@code ooffset} and has length {@code len}. The
     * result is {@code false} if and only if at least one of the following
     * is true:
     *
     * 此 String 对象的子字符串将与其他参数的子字符串进行比较。
     * 如果这些子串表示相同的字符序列，则结果为真。
     * 要比较的 String 对象的子字符串从索引 toffset 开始，长度为len 。
     * 要比较的其他子字符串从索引 ooffset 开始，长度为 len 。
     * 当且仅当至少满足下列条件之一时，结果为 false ：
     *
     * <ul><li>{@code toffset} is negative.
     * <li>{@code ooffset} is negative.
     * <li>{@code toffset+len} is greater than the length of this
     * {@code String} object.
     * <li>{@code ooffset+len} is greater than the length of the other
     * argument.
     * <li>There is some nonnegative integer <i>k</i> less than {@code len}
     * such that:
     * {@code this.charAt(toffset + }<i>k</i>{@code ) != other.charAt(ooffset + }
     * <i>k</i>{@code )}
     * </ul>
     *
     * <p>Note that this method does <em>not</em> take locale into account.  The
     * {@link java.text.Collator} class provides locale-sensitive comparison.
     *
     * 请注意，此方法 不 考虑区域设置。
     * java.text.Collator 类提供区分区域敏感的比较。
     *
     * @param   toffset   the starting offset of the subregion in this string.
     *                    此字符串中子区域的起始偏移量。
     *
     * @param   other     the string argument.
     *                    字符串参数。
     *
     * @param   ooffset   the starting offset of the subregion in the string
     *                    argument.
     *                    字符串参数中子区域的起始偏移量。
     *
     * @param   len       the number of characters to compare.
     *                    要比较的字符数。
     *
     * @return  {@code true} if the specified subregion of this string
     *          exactly matches the specified subregion of the string argument;
     *          {@code false} otherwise.
     *
     *           返回 true 如果此字符串的指定子区域与字符串参数的指定子区域完全匹配;
     *          否则 false 。
     */
    public boolean regionMatches(int toffset, String other, int ooffset, int len) {
        byte tv[] = value;
        byte ov[] = other.value;
        // Note: toffset, ooffset, or len might be near -1>>>1.
        if ((ooffset < 0) || (toffset < 0) ||
             (toffset > (long)length() - len) ||
             (ooffset > (long)other.length() - len)) {
            return false;
        }
        if (coder() == other.coder()) {
            if (!isLatin1() && (len > 0)) {
                toffset = toffset << 1;
                ooffset = ooffset << 1;
                len = len << 1;
            }
            while (len-- > 0) {
                if (tv[toffset++] != ov[ooffset++]) {
                    return false;
                }
            }
        } else {
            if (coder() == LATIN1) {
                while (len-- > 0) {
                    if (StringLatin1.getChar(tv, toffset++) !=
                        StringUTF16.getChar(ov, ooffset++)) {
                        return false;
                    }
                }
            } else {
                while (len-- > 0) {
                    if (StringUTF16.getChar(tv, toffset++) !=
                        StringLatin1.getChar(ov, ooffset++)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Tests if two string regions are equal.
     * 测试两个字符串区域是否相等。
     *
     * <p>
     * A substring of this {@code String} object is compared to a substring
     * of the argument {@code other}. The result is {@code true} if these
     * substrings represent character sequences that are the same, ignoring
     * case if and only if {@code ignoreCase} is true. The substring of
     * this {@code String} object to be compared begins at index
     * {@code toffset} and has length {@code len}. The substring of
     * {@code other} to be compared begins at index {@code ooffset} and
     * has length {@code len}. The result is {@code false} if and only if
     * at least one of the following is true:
     *
     * 将此 String 对象的子字符串与参数 other 的子字符串进行比较。
     * 如果这些子串表示相同的字符序列，则结果为 true ，
     * 当且仅当 ignoreCase 为真时才忽略大小写。
     * 要比较的 String 对象的子字符串从索引 toffset 开始，长度为 len 。
     * 要比较的 other 子字符串从索引 ooffset 开始，长度为 len 。
     * 当且仅当至少满足下列条件之一时，结果为 false ：
     *
     * <ul><li>{@code toffset} is negative.
     * <li>{@code ooffset} is negative.
     * <li>{@code toffset+len} is greater than the length of this
     * {@code String} object.
     * <li>{@code ooffset+len} is greater than the length of the other
     * argument.
     * <li>{@code ignoreCase} is {@code false} and there is some nonnegative
     * integer <i>k</i> less than {@code len} such that:
     * <blockquote><pre>
     * this.charAt(toffset+k) != other.charAt(ooffset+k)
     * </pre></blockquote>
     * <li>{@code ignoreCase} is {@code true} and there is some nonnegative
     * integer <i>k</i> less than {@code len} such that:
     * <blockquote><pre>
     * Character.toLowerCase(Character.toUpperCase(this.charAt(toffset+k))) !=
     Character.toLowerCase(Character.toUpperCase(other.charAt(ooffset+k)))
     * </pre></blockquote>
     * </ul>
     *
     * <p>Note that this method does <em>not</em> take locale into account,
     * and will result in unsatisfactory results for certain locales when
     * {@code ignoreCase} is {@code true}.  The {@link java.text.Collator} class
     * provides locale-sensitive comparison.
     *
     * 请注意，此方法 不 将区域设置考虑在内，
     * 并且当 ignoreCase 为 true 时，某些区域设置会导致不满意的结果。
     * java.text.Collator 类提供区分区域敏感的比较。
     *
     * @param   ignoreCase   if {@code true}, ignore case when comparing
     *                       characters.
     *                       if true ，在比较字符时忽略大小写。
     *
     * @param   toffset      the starting offset of the subregion in this
     *                       string.
     *                       此字符串中子区域的起始偏移量。
     *
     * @param   other        the string argument.
     *                       字符串参数。
     *
     * @param   ooffset      the starting offset of the subregion in the string
     *                       argument.
     *                       字符串参数中子区域的起始偏移量。
     *
     * @param   len          the number of characters to compare.
     *                       要比较的字符数。
     *
     * @return  {@code true} if the specified subregion of this string
     *          matches the specified subregion of the string argument;
     *          {@code false} otherwise. Whether the matching is exact
     *          or case insensitive depends on the {@code ignoreCase}
     *          argument.
     *          返回 true 如果此字符串的指定子区域与字符串参数的指定子区域匹配; 否则 false。
     *          匹配是精确匹配还是不区分大小写取决于 ignoreCase 参数。
     *
     */
    public boolean regionMatches(boolean ignoreCase, int toffset,
            String other, int ooffset, int len) {
        if (!ignoreCase) {
            return regionMatches(toffset, other, ooffset, len);
        }
        // Note: toffset, ooffset, or len might be near -1>>>1.
        if ((ooffset < 0) || (toffset < 0)
                || (toffset > (long)length() - len)
                || (ooffset > (long)other.length() - len)) {
            return false;
        }
        byte tv[] = value;
        byte ov[] = other.value;
        if (coder() == other.coder()) {
            return isLatin1()
              ? StringLatin1.regionMatchesCI(tv, toffset, ov, ooffset, len)
              : StringUTF16.regionMatchesCI(tv, toffset, ov, ooffset, len);
        }
        return isLatin1()
              ? StringLatin1.regionMatchesCI_UTF16(tv, toffset, ov, ooffset, len)
              : StringUTF16.regionMatchesCI_Latin1(tv, toffset, ov, ooffset, len);
    }

    /**
     * Tests if the substring of this string beginning at the
     * specified index starts with the specified prefix.
     *
     * 测试从指定索引开始的此字符串的子字符串是否以指定的前缀开头。
     *
     * @param   prefix    the prefix. 前缀。
     * @param   toffset   where to begin looking in this string.
     *                    从哪里开始查看此字符串。
     *
     * @return  {@code true} if the character sequence represented by the
     *          argument is a prefix of the substring of this object starting
     *          at index {@code toffset}; {@code false} otherwise.
     *          The result is {@code false} if {@code toffset} is
     *          negative or greater than the length of this
     *          {@code String} object; otherwise the result is the same
     *          as the result of the expression
     *
     *          返回 true 如果参数表示的字符序列是从索引 offset 开始的该对象的子字符串的前缀;
     *          否则 false 。 如果 toffset 为负数或大于此 String 对象的长度，则结果为 false ;
     *          否则结果与表达式的结果相同。
     *
     *          <pre>
     *          this.substring(toffset).startsWith(prefix)
     *          </pre>
     */
    public boolean startsWith(String prefix, int toffset) {
        // Note: toffset might be near -1>>>1.
        if (toffset < 0 || toffset > length() - prefix.length()) {
            return false;
        }
        byte ta[] = value;
        byte pa[] = prefix.value;
        int po = 0;
        int pc = pa.length;
        if (coder() == prefix.coder()) {
            int to = isLatin1() ? toffset : toffset << 1;
            while (po < pc) {
                if (ta[to++] != pa[po++]) {
                    return false;
                }
            }
        } else {
            if (isLatin1()) {  // && pcoder == UTF16
                return false;
            }
            // coder == UTF16 && pcoder == LATIN1)
            while (po < pc) {
                if (StringUTF16.getChar(ta, toffset++) != (pa[po++] & 0xff)) {
                    return false;
               }
            }
        }
        return true;
    }

    /**
     * Tests if this string starts with the specified prefix.
     * 测试此字符串是否以指定的前缀开头。
     *
     * @param   prefix   the prefix.
     * @return  {@code true} if the character sequence represented by the
     *          argument is a prefix of the character sequence represented by
     *          this string; {@code false} otherwise.
     *          Note also that {@code true} will be returned if the
     *          argument is an empty string or is equal to this
     *          {@code String} object as determined by the
     *          {@link #equals(Object)} method.
     *
     *          返回 true 如果参数表示的字符序列是该字符串表示的字符序列的前缀; 否则 false 。
     *          另请注意，如果参数为空字符串或等于 equals（Object） 方法确定的 String 对象，
     *          则将返回 true 。
     *
     * @since   1.0
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 测试此字符串是否以指定的后缀结尾。
     *
     * @param   suffix   the suffix.
     * @return  {@code true} if the character sequence represented by the
     *          argument is a suffix of the character sequence represented by
     *          this object; {@code false} otherwise. Note that the
     *          result will be {@code true} if the argument is the
     *          empty string or is equal to this {@code String} object
     *          as determined by the {@link #equals(Object)} method.
     *
     *          返回 true 如果参数表示的字符序列是该对象表示的字符序列的后缀; 否则 false 。
     *          请注意，如果参数为空字符串，或者等于 equals（Object） 方法确定的 String 对象，
     *          则结果为 true 。
     *
     */
    public boolean endsWith(String suffix) {
        return startsWith(suffix, length() - suffix.length());
    }

    /**
     * Returns a hash code for this string. The hash code for a
     * {@code String} object is computed as
     * 返回此字符串的哈希码。 String 对象的哈希码计算为
     *
     * <blockquote><pre>
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * </pre></blockquote>
     * using {@code int} arithmetic, where {@code s[i]} is the
     * <i>i</i>th character of the string, {@code n} is the length of
     * the string, and {@code ^} indicates exponentiation.
     * (The hash value of the empty string is zero.)
     *
     * 使用 int 算术，其中 s [i] 是字符串的 i  字符，
     * n 是字符串的长度，^ 表示取幂。 （空字符串的哈希值为零。）
     *
     * @return  a hash code value for this object.
     *          此对象的哈希码值。
     */
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            hash = h = isLatin1() ? StringLatin1.hashCode(value)
                                  : StringUTF16.hashCode(value);
        }
        return h;
    }

    /**
     * Returns the index within this string of the first occurrence of
     * the specified character. If a character with value
     * {@code ch} occurs in the character sequence represented by
     * this {@code String} object, then the index (in Unicode
     * code units) of the first such occurrence is returned. For
     * values of {@code ch} in the range from 0 to 0xFFFF
     * (inclusive), this is the smallest value <i>k</i> such that:
     *
     * 返回指定字符第一次出现的字符串中的索引。
     * 如果在此 String 对象表示的字符序列中出现值为 ch 的字符，则返回第一个此类事件的索引（以Unicode代码为单位）。
     * 对于 ch 的值，范围从0到0xFFFF（包括），这是最小值 k  ，这样：
     *
     * <blockquote><pre>
     * this.charAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. For other values of {@code ch}, it is the
     * smallest value <i>k</i> such that:
     * <blockquote><pre>
     * this.codePointAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. In either case, if no such character occurs in this
     * string, then {@code -1} is returned.
     *
     * 在任何一种情况下，如果此字符串中没有出现此类字符，则返回 -1 。
     *
     * @param   ch   a character (Unicode code point).
     *               一个字符（Unicode代码点）。
     *
     * @return  the index of the first occurrence of the character in the
     *          character sequence represented by this object, or
     *          {@code -1} if the character does not occur.
     *
     *          返回 此对象表示的字符序列中第一次出现的字符的索引，如果未出现该字符，则为 -1 。
     *
     */
    public int indexOf(int ch) {
        return indexOf(ch, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified character, starting the search at the specified index.
     *
     * 返回指定字符第一次出现的此字符串中的索引，从指定索引处开始搜索。
     *
     * <p>
     * If a character with value {@code ch} occurs in the
     * character sequence represented by this {@code String}
     * object at an index no smaller than {@code fromIndex}, then
     * the index of the first such occurrence is returned. For values
     * of {@code ch} in the range from 0 to 0xFFFF (inclusive),
     * this is the smallest value <i>k</i> such that:
     *
     * 如果在 fromIndex 的索引处，此 String 对象表示的字符序列中出现值为 ch 的字符，则返回第一个此类出现的索引。
     * 对于 ch 的值，范围从0到0xFFFF（包括），这是最小值 k ，这样：
     *
     * <blockquote><pre>
     * (this.charAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &gt;= fromIndex)
     * </pre></blockquote>
     * is true. For other values of {@code ch}, it is the
     * smallest value <i>k</i> such that:
     * <blockquote><pre>
     * (this.codePointAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &gt;= fromIndex)
     * </pre></blockquote>
     * is true. In either case, if no such character occurs in this
     * string at or after position {@code fromIndex}, then
     * {@code -1} is returned.
     *
     * 在任何一种情况下，如果在 fromIndex 位置或之后该字符串中没有出现这样的字符，则返回 -1 。
     *
     * <p>
     * There is no restriction on the value of {@code fromIndex}. If it
     * is negative, it has the same effect as if it were zero: this entire
     * string may be searched. If it is greater than the length of this
     * string, it has the same effect as if it were equal to the length of
     * this string: {@code -1} is returned.
     *
     * fromIndex 的值没有限制。 如果它是负数，则它具有与零相同的效果：可以搜索整个字符串。
     * 如果它大于此字符串的长度，则它具有与该字符串的长度相同的效果：返回 -1 。
     *
     * <p>All indices are specified in {@code char} values
     * (Unicode code units).
     *
     * 所有索引都以 char 值（Unicode代码单位）指定。
     *
     * @param   ch          a character (Unicode code point).
     *                      一个字符（Unicode代码点）。
     *
     * @param   fromIndex   the index to start the search from.
     *                      从中开始搜索的索引。
     *
     * @return  the index of the first occurrence of the character in the
     *          character sequence represented by this object that is greater
     *          than or equal to {@code fromIndex}, or {@code -1}
     *          if the character does not occur.
     *
     *          返回 此对象表示的字符序列中第一次出现的字符的索引，大于或等于 fromIndex ，
     *          如果字符未出现，则为 -1 。
     */
    public int indexOf(int ch, int fromIndex) {
        return isLatin1() ? StringLatin1.indexOf(value, ch, fromIndex)
                          : StringUTF16.indexOf(value, ch, fromIndex);
    }

    /**
     * Returns the index within this string of the last occurrence of
     * the specified character. For values of {@code ch} in the
     * range from 0 to 0xFFFF (inclusive), the index (in Unicode code
     * units) returned is the largest value <i>k</i> such that:
     *
     * 返回指定字符最后一次出现的字符串中的索引。
     * 对于 ch 的值，范围从0到0xFFFF（含），返回的索引（以Unicode代码为单位）是最大值 k ，这样：
     *
     * <blockquote><pre>
     * this.charAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. For other values of {@code ch}, it is the
     * largest value <i>k</i> such that:
     *
     * 对于 ch 的其他值，它是最大值 k ，这样：
     *
     * <blockquote><pre>
     * this.codePointAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true.  In either case, if no such character occurs in this
     * string, then {@code -1} is returned.  The
     * {@code String} is searched backwards starting at the last
     * character.
     *
     * 在任何一种情况下，如果此字符串中没有出现此类字符，则返回 -1 。
     * 从最后一个字符开始向后搜索 String 。ss
     *
     * @param   ch   a character (Unicode code point).
     *               一个字符（Unicode代码点）。
     *
     * @return  the index of the last occurrence of the character in the
     *          character sequence represented by this object, or
     *          {@code -1} if the character does not occur.
     *          此对象表示的字符序列中最后一次出现的索引，如果未出现该字符，则为 -1 。
     */
    public int lastIndexOf(int ch) {
        return lastIndexOf(ch, length() - 1);
    }

    /**
     * Returns the index within this string of the last occurrence of
     * the specified character, searching backward starting at the
     * specified index. For values of {@code ch} in the range
     * from 0 to 0xFFFF (inclusive), the index returned is the largest
     * value <i>k</i> such that:
     *
     * 返回指定字符最后一次出现的字符串中的索引，从指定的索引开始向后搜索。
     * 对于 ch 的值，范围从 0 到 0xFFFF（含），返回的索引是最大值 k ，这样：
     *
     * <blockquote><pre>
     * (this.charAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &lt;= fromIndex)
     * </pre></blockquote>
     * is true. For other values of {@code ch}, it is the
     * largest value <i>k</i> such that:
     * 对于 ch 的其他值，它是最大值 k ，这样：
     *
     * <blockquote><pre>
     * (this.codePointAt(<i>k</i>) == ch) {@code &&} (<i>k</i> &lt;= fromIndex)
     * </pre></blockquote>
     * is true. In either case, if no such character occurs in this
     * string at or before position {@code fromIndex}, then
     * {@code -1} is returned.
     *
     * 在任何一种情况下，如果在 fromIndex 位置或之前此字符串中没有出现此类字符，则返回 -1 。
     *
     * <p>All indices are specified in {@code char} values
     * (Unicode code units).
     *
     * 所有索引都以 char 值（Unicode代码单位）指定。
     *
     * @param   ch          a character (Unicode code point).
     *                      一个字符（Unicode代码点）。
     *
     * @param   fromIndex   the index to start the search from. There is no
     *          restriction on the value of {@code fromIndex}. If it is
     *          greater than or equal to the length of this string, it has
     *          the same effect as if it were equal to one less than the
     *          length of this string: this entire string may be searched.
     *          If it is negative, it has the same effect as if it were -1:
     *          -1 is returned.
     *          从中开始搜索的索引。fromIndex 的值没有限制。
     *          如果它大于或等于该字符串的长度，则它具有相同的效果，
     *          如果它等于小于该字符串长度的一个：可以搜索整个字符串。
     *          如果是负数，则它具有与-1相同的效果：返回 -1。
     *
     * @return  the index of the last occurrence of the character in the
     *          character sequence represented by this object that is less
     *          than or equal to {@code fromIndex}, or {@code -1}
     *          if the character does not occur before that point.
     *
     *          此对象表示的字符序列中最后一次出现的字符的索引，
     *          该字符序列小于或等于 fromIndex ，如果该字符在该点之前未出现，则为 -1 。
     */
    public int lastIndexOf(int ch, int fromIndex) {
        return isLatin1() ? StringLatin1.lastIndexOf(value, ch, fromIndex)
                          : StringUTF16.lastIndexOf(value, ch, fromIndex);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring.
     * 返回指定子字符串第一次出现的字符串中的索引。
     *
     * <p>The returned index is the smallest value {@code k} for which:
     *   返回的索引是 k 的最小值，其中：
     * <pre>{@code
     * this.startsWith(str, k)
     * }</pre>
     * If no such value of {@code k} exists, then {@code -1} is returned.
     * 如果不存在 k 的此类值，则返回 -1 。
     *
     * @param   str   the substring to search for.
     *                要搜索的子字符串。
     *
     * @return  the index of the first occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     *          指定子字符串第一次出现的索引，如果没有这种情况，则为 -1 。
     *
     */
    public int indexOf(String str) {
        if (coder() == str.coder()) {
            return isLatin1() ? StringLatin1.indexOf(value, str.value)
                              : StringUTF16.indexOf(value, str.value);
        }
        if (coder() == LATIN1) {  // str.coder == UTF16
            return -1;
        }
        return StringUTF16.indexOfLatin1(value, str.value);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring, starting at the specified index.
     *
     * 从指定的索引处开始，返回指定子字符串第一次出现的字符串中的索引。
     *
     * <p>The returned index is the smallest value {@code k} for which:
     *    返回的索引是 k 的最小值，其中：
     *
     * <pre>{@code
     *     k >= Math.min(fromIndex, this.length()) &&
     *                   this.startsWith(str, k)
     * }</pre>
     * If no such value of {@code k} exists, then {@code -1} is returned.
     * 如果不存在 k 的此类值，则返回 -1 。
     *
     * @param   str         the substring to search for.
     *                      要搜索的子字符串。
     * @param   fromIndex   the index from which to start the search.
     *                      从中开始搜索的索引。
     *
     * @return  the index of the first occurrence of the specified substring,
     *          starting at the specified index,
     *          or {@code -1} if there is no such occurrence.
     *          从指定索引开始的指定子字符串第一次出现的索引，如果没有这种情况，则为 -1 。
     *
     */
    public int indexOf(String str, int fromIndex) {
        return indexOf(value, coder(), length(), str, fromIndex);
    }

    /**
     * Code shared by String and AbstractStringBuilder to do searches. The
     * source is the character array being searched, and the target
     * is the string being searched for.
     *
     * String 和 AbstractStringBuilder 共享的代码进行搜索。
     * 源是要搜索的字符数组，目标是要搜索的字符串。
     *
     * @param   src       the characters being searched.
     *                    正在搜索的字符。
     *
     * @param   srcCoder  the coder of the source string.
     *                    源字符串的编码器。
     *
     * @param   srcCount  length of the source string.
     *                    源字符串的长度。
     *
     * @param   tgtStr    the characters being searched for.
     *                    要搜索的字符。
     *
     * @param   fromIndex the index to begin searching from.
     *                    要开始搜索的索引。
     */
    static int indexOf(byte[] src, byte srcCoder, int srcCount,
                       String tgtStr, int fromIndex) {
        byte[] tgt    = tgtStr.value;
        byte tgtCoder = tgtStr.coder();
        int tgtCount  = tgtStr.length();

        if (fromIndex >= srcCount) {
            return (tgtCount == 0 ? srcCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (tgtCount == 0) {
            return fromIndex;
        }
        if (tgtCount > srcCount) {
            return -1;
        }
        if (srcCoder == tgtCoder) {
            return srcCoder == LATIN1
                ? StringLatin1.indexOf(src, srcCount, tgt, tgtCount, fromIndex)
                : StringUTF16.indexOf(src, srcCount, tgt, tgtCount, fromIndex);
        }
        if (srcCoder == LATIN1) {    //  && tgtCoder == UTF16
            return -1;
        }
        // srcCoder == UTF16 && tgtCoder == LATIN1) {
        return StringUTF16.indexOfLatin1(src, srcCount, tgt, tgtCount, fromIndex);
    }

    /**
     * Returns the index within this string of the last occurrence of the
     * specified substring.  The last occurrence of the empty string ""
     * is considered to occur at the index value {@code this.length()}.
     *
     * 返回指定子字符串最后一次出现的字符串中的索引。
     * 空字符串“”的最后一次出现被认为是在索引值 this.length（） 处发生的。
     *
     * <p>The returned index is the largest value {@code k} for which:
     *    返回的索引是 k 的最大值，其中：
     *
     * <pre>{@code
     * this.startsWith(str, k)
     * }</pre>
     * If no such value of {@code k} exists, then {@code -1} is returned.
     * 如果不存在 k 的此类值，则返回 -1 。
     *
     * @param   str   the substring to search for.
     *                要搜索的子字符串。
     *
     * @return  the index of the last occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     *          指定子字符串最后一次出现的索引，如果没有这种情况，则为 -1 。
     */
    public int lastIndexOf(String str) {
        return lastIndexOf(str, length());
    }

    /**
     * Returns the index within this string of the last occurrence of the
     * specified substring, searching backward starting at the specified index.
     * 返回指定子字符串最后一次出现的字符串中的索引，从指定索引开始向后搜索。
     *
     * <p>The returned index is the largest value {@code k} for which:
     *    返回的索引是 k 的最大值，其中：
     *
     * <pre>{@code
     *     k <= Math.min(fromIndex, this.length()) &&
     *                   this.startsWith(str, k)
     * }</pre>
     * If no such value of {@code k} exists, then {@code -1} is returned.
     * 如果不存在 k 的此类值，则返回 -1 。
     *
     * @param   str         the substring to search for.
     *                      要搜索的子字符串。
     *
     * @param   fromIndex   the index to start the search from.
     *                      从中开始搜索的索引。
     *
     * @return  the index of the last occurrence of the specified substring,
     *          searching backward from the specified index,
     *          or {@code -1} if there is no such occurrence.
     *          指定子字符串最后一次出现的索引，从指定索引向后搜索，如果没有这种情况，则为 -1 。
     */
    public int lastIndexOf(String str, int fromIndex) {
        return lastIndexOf(value, coder(), length(), str, fromIndex);
    }

    /**
     * Code shared by String and AbstractStringBuilder to do searches. The
     * source is the character array being searched, and the target
     * is the string being searched for.
     *
     * String 和 AbstractStringBuilder 共享的代码进行搜索。
     * 源是要搜索的字符数组，目标是要搜索的字符串。
     *
     * @param   src         the characters being searched.
     *                      正在搜索的字符。
     *
     * @param   srcCoder    coder handles the mapping between bytes/chars
     *                      编码器处理字节/字符之间的映射
     *
     * @param   srcCount    count of the source string.
     *                      源字符串的计数。
     *
     * @param   tgt         the characters being searched for.
     *                      要搜索的字符。
     *
     * @param   fromIndex   the index to begin searching from.
     *                      要开始搜索的索引。
     */
    static int lastIndexOf(byte[] src, byte srcCoder, int srcCount,
                           String tgtStr, int fromIndex) {
        byte[] tgt = tgtStr.value;
        byte tgtCoder = tgtStr.coder();
        int tgtCount = tgtStr.length();
        /*
         * Check arguments; return immediately where possible. For
         * consistency, don't check for null str.
         */
        int rightIndex = srcCount - tgtCount;
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }
        if (fromIndex < 0) {
            return -1;
        }
        /* Empty string always matches. */
        if (tgtCount == 0) {
            return fromIndex;
        }
        if (srcCoder == tgtCoder) {
            return srcCoder == LATIN1
                ? StringLatin1.lastIndexOf(src, srcCount, tgt, tgtCount, fromIndex)
                : StringUTF16.lastIndexOf(src, srcCount, tgt, tgtCount, fromIndex);
        }
        if (srcCoder == LATIN1) {    // && tgtCoder == UTF16
            return -1;
        }
        // srcCoder == UTF16 && tgtCoder == LATIN1
        return StringUTF16.lastIndexOfLatin1(src, srcCount, tgt, tgtCount, fromIndex);
    }

    /**
     * Returns a string that is a substring of this string. The
     * substring begins with the character at the specified index and
     * extends to the end of this string. <p>
     * 返回一个字符串，该字符串是此字符串的子字符串。
     * 子字符串以指定索引处的字符开头，并延伸到此字符串的末尾。
     *
     * Examples:
     * 例子：
     * <blockquote><pre>
     * "unhappy".substring(2) returns "happy"
     * "Harbison".substring(3) returns "bison"
     * "emptiness".substring(9) returns "" (an empty string)
     * </pre></blockquote>
     *
     * @param      beginIndex   the beginning index, inclusive.
     *                          起始索引，包括在内。
     *
     * @return     the specified substring.
     *              the specified substring.
     *
     * @exception  IndexOutOfBoundsException  if
     *             {@code beginIndex} is negative or larger than the
     *             length of this {@code String} object.
     *             如果 beginIndex 为负数或大于此 String 对象的长度。
     *
     */
    public String substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        int subLen = length() - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        if (beginIndex == 0) {
            return this;
        }
        return isLatin1() ? StringLatin1.newString(value, beginIndex, subLen)
                          : StringUTF16.newString(value, beginIndex, subLen);
    }

    /**
     * Returns a string that is a substring of this string. The
     * substring begins at the specified {@code beginIndex} and
     * extends to the character at index {@code endIndex - 1}.
     * Thus the length of the substring is {@code endIndex-beginIndex}.
     *
     * 返回一个字符串，该字符串是此字符串的子字符串。
     * 子字符串从指定的 beginIndex 开始，并扩展到索引 endIndex  -  1 处的字符。
     * 因此子串的长度是 endIndex-beginIndex 。
     *
     * <p>
     * Examples:
     * <blockquote><pre>
     * "hamburger".substring(4, 8) returns "urge"
     * "smiles".substring(1, 5) returns "mile"
     * </pre></blockquote>
     *
     * @param      beginIndex   the beginning index, inclusive.
     *                          起始索引，包括在内。
     *
     * @param      endIndex     the ending index, exclusive.
     *                          结束索引，排除在外。
     *
     * @return     the specified substring.
     *              指定的子字符串。
     *
     * @exception  IndexOutOfBoundsException  if the
     *             {@code beginIndex} is negative, or
     *             {@code endIndex} is larger than the length of
     *             this {@code String} object, or
     *             {@code beginIndex} is larger than
     *             {@code endIndex}.
     *
     *             如果 beginIndex 为负数，或者 endIndex 大于此 String 对象的长度，
     *             或者 beginIndex 大于 endIndex 。
     *
     */
    public String substring(int beginIndex, int endIndex) {
        int length = length();
        checkBoundsBeginEnd(beginIndex, endIndex, length);
        int subLen = endIndex - beginIndex;
        if (beginIndex == 0 && endIndex == length) {
            return this;
        }
        return isLatin1() ? StringLatin1.newString(value, beginIndex, subLen)
                          : StringUTF16.newString(value, beginIndex, subLen);
    }

    /**
     * Returns a character sequence that is a subsequence of this sequence.
     *
     * 返回作为此序列的子序列的字符序列。
     *
     * <p> An invocation of this method of the form
     *    调用此方法的形式
     *
     * <blockquote><pre>
     * str.subSequence(begin,&nbsp;end)</pre></blockquote>
     *
     * behaves in exactly the same way as the invocation
     * 行为与调用完全相同
     *
     * <blockquote><pre>
     * str.substring(begin,&nbsp;end)</pre></blockquote>
     *
     * @apiNote
     * This method is defined so that the {@code String} class can implement
     * the {@link CharSequence} interface.
     *
     * 定义此方法，以便 String 类可以实现 CharSequence 接口。
     *
     * @param   beginIndex   the begin index, inclusive.
     *                       开头索引，包括在内。
     *
     * @param   endIndex     the end index, exclusive.
     *                       最终索引，排除在外
     *
     * @return  the specified subsequence.
     *           指定的子序列。
     *
     * @throws  IndexOutOfBoundsException
     *          if {@code beginIndex} or {@code endIndex} is negative,
     *          if {@code endIndex} is greater than {@code length()},
     *          or if {@code beginIndex} is greater than {@code endIndex}
     *          如果 beginIndex 或 endIndex 为负数，如果 endIndex 大于 length（） ，
     *          或者 beginIndex 大于 endIndex
     *
     * @since 1.4
     * @spec JSR-51
     */
    public CharSequence subSequence(int beginIndex, int endIndex) {
        return this.substring(beginIndex, endIndex);
    }

    /**
     * Concatenates the specified string to the end of this string.
     * 将指定的字符串连接到此字符串的末尾。
     *
     * <p>
     * If the length of the argument string is {@code 0}, then this
     * {@code String} object is returned. Otherwise, a
     * {@code String} object is returned that represents a character
     * sequence that is the concatenation of the character sequence
     * represented by this {@code String} object and the character
     * sequence represented by the argument string.<p>
     *
     * 如果参数字符串的长度为 0 ，则返回此 String 对象。
     * 否则，返回一个 String 对象，该对象表示一个字符序列，
     * 该字符序列是由此 String 对象表示的字符序列和由参数字符串表示的字符序列的串联。
     *
     * Examples:
     * <blockquote><pre>
     * "cares".concat("s") returns "caress"
     * "to".concat("get").concat("her") returns "together"
     * </pre></blockquote>
     *
     * @param   str   the {@code String} that is concatenated to the end
     *                of this {@code String}.
     *                String 连接到此 String 的末尾。
     *
     * @return  a string that represents the concatenation of this object's
     *          characters followed by the string argument's characters.
     *          一个字符串，表示此对象的字符串联后跟字符串参数的字符。
     */
    public String concat(String str) {
        int olen = str.length();
        if (olen == 0) {
            return this;
        }
        if (coder() == str.coder()) {
            byte[] val = this.value;
            byte[] oval = str.value;
            int len = val.length + oval.length;
            byte[] buf = Arrays.copyOf(val, len);
            System.arraycopy(oval, 0, buf, val.length, oval.length);
            return new String(buf, coder);
        }
        int len = length();
        byte[] buf = StringUTF16.newBytesFor(len + olen);
        getBytes(buf, 0, UTF16);
        str.getBytes(buf, len, UTF16);
        return new String(buf, UTF16);
    }

    /**
     * Returns a string resulting from replacing all occurrences of
     * {@code oldChar} in this string with {@code newChar}.
     *
     * 返回使用 newChar 替换此字符串中所有出现的 oldChar 而生成的字符串。
     *
     * <p>
     * If the character {@code oldChar} does not occur in the
     * character sequence represented by this {@code String} object,
     * then a reference to this {@code String} object is returned.
     * Otherwise, a {@code String} object is returned that
     * represents a character sequence identical to the character sequence
     * represented by this {@code String} object, except that every
     * occurrence of {@code oldChar} is replaced by an occurrence
     * of {@code newChar}.
     *
     * 如果在此 String 对象表示的字符序列中未出现字符 oldChar ，则返回对此 String 对象的引用。
     * 否则，将返回一个 String 对象，该对象表示与此 String 对象表示的字符序列相同的字符序列，
     * 但每次出现 oldChar 都会被替换为 newChar 。
     *
     * <p>
     * Examples:
     * <blockquote><pre>
     * "mesquite in your cellar".replace('e', 'o')
     *         returns "mosquito in your collar"
     * "the war of baronets".replace('r', 'y')
     *         returns "the way of bayonets"
     * "sparring with a purple porpoise".replace('p', 't')
     *         returns "starring with a turtle tortoise"
     * "JonL".replace('q', 'x') returns "JonL" (no change)
     * </pre></blockquote>
     *
     * @param   oldChar   the old character.
     *                    旧字符
     *
     * @param   newChar   the new character.
     *                    新字符
     *
     * @return  a string derived from this string by replacing every
     *          occurrence of {@code oldChar} with {@code newChar}.
     *
     *          通过用 newChar 替换每次出现的 oldChar ，从该字符串派生的字符串。
     *
     */
    public String replace(char oldChar, char newChar) {
        if (oldChar != newChar) {
            String ret = isLatin1() ? StringLatin1.replace(value, oldChar, newChar)
                                    : StringUTF16.replace(value, oldChar, newChar);
            if (ret != null) {
                return ret;
            }
        }
        return this;
    }

    /**
     * Tells whether or not this string matches the given
     * 判断此字符串是否与给定字符串匹配
     * <a href="../util/regex/Pattern.html#sum">regular expression</a>.
     * 正则表达式
     *
     * <p> An invocation of this method of the form
     * <i>str</i>{@code .matches(}<i>regex</i>{@code )} yields exactly the
     * same result as the expression
     *
     * 调用 str 的 matches（regex） 形式的此方法会产生与表达式完全相同的结果
     *
     *
     * <blockquote>
     * {@link java.util.regex.Pattern}.{@link java.util.regex.Pattern#matches(String,CharSequence)
     * matches(<i>regex</i>, <i>str</i>)}
     * </blockquote>
     *
     * @param   regex
     *          the regular expression to which this string is to be matched
     *          要与此字符串匹配的正则表达式
     *
     *
     * @return  {@code true} if, and only if, this string matches the
     *          given regular expression
     *          返回 true 当且仅当此字符串与给定的正则表达式匹配时
     *
     *
     * @throws  PatternSyntaxException  模式语法异常
     *          if the regular expression's syntax is invalid
     *          如果正则表达式的语法无效
     *
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public boolean matches(String regex) {
        return Pattern.matches(regex, this);
    }

    /**
     * Returns true if and only if this string contains the specified
     * sequence of char values.
     * 当且仅当此字符串包含指定的 char 值序列时，才返回 true。
     *
     * @param s the sequence to search for
     *          要搜索的序列
     *
     * @return true if this string contains {@code s}, false otherwise
     *          如果此字符串包含 s ，则返回 true，否则返回 false
     *
     * @since 1.5
     */
    public boolean contains(CharSequence s) {
        return indexOf(s.toString()) >= 0;
    }

    /**
     * Replaces the first substring of this string that matches the given
     * 替换此字符串中与给定匹配的第一个子字符串
     *
     * <a href="../util/regex/Pattern.html#sum">regular expression</a> with the
     * given replacement.
     *
     * <p> An invocation of this method of the form
     *    调用此方法的形式
     *
     * <i>str</i>{@code .replaceFirst(}<i>regex</i>{@code ,} <i>repl</i>{@code )}
     * yields exactly the same result as the expression
     *
     *  str.replaceFirst（regex， repl）产生与表达式完全相同的结果
     *
     *
     * <blockquote>
     * <code>
     * {@link java.util.regex.Pattern}.{@link
     * java.util.regex.Pattern#compile compile}(<i>regex</i>).{@link
     * java.util.regex.Pattern#matcher(java.lang.CharSequence) matcher}(<i>str</i>).{@link
     * java.util.regex.Matcher#replaceFirst replaceFirst}(<i>repl</i>)
     * </code>
     * </blockquote>
     *
     *<p>
     * Note that backslashes ({@code \}) and dollar signs ({@code $}) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string; see
     * {@link java.util.regex.Matcher#replaceFirst}.
     * Use {@link java.util.regex.Matcher#quoteReplacement} to suppress the special
     * meaning of these characters, if desired.
     *
     * 请注意，替换字符串中的反斜杠 \ 和美元符号 $ 可能会导致结果与将其视为文字替换字符串时的结果不同;
     * 请参阅 java.util.regex.Matcher＃replaceFirst。 如果需要，使用 java.util.regex.Matcher＃quoteReplacement
     * 来抑制这些字符的特殊含义。
     *
     *
     * @param   regex
     *          the regular expression to which this string is to be matched
     *          要与此字符串匹配的正则表达式
     *
     * @param   replacement
     *          the string to be substituted for the first match
     *          要替换第一个匹配的字符串
     *
     *
     * @return  The resulting {@code String}
     *
     * @throws  PatternSyntaxException 模式语法异常s
     *          if the regular expression's syntax is invalid
     *          如果正则表达式的语法无效
     *
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replaceFirst(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceFirst(replacement);
    }

    /**
     * Replaces each substring of this string that matches the given
     * <a href="../util/regex/Pattern.html#sum">regular expression</a> with the
     * given replacement.
     *
     * 将给定替换的给定正则表达式匹配的此字符串的每个子字符串替换。
     *
     * <p> An invocation of this method of the form
     * <i>str</i>{@code .replaceAll(}<i>regex</i>{@code ,} <i>repl</i>{@code )}
     * yields exactly the same result as the expression
     *
     * 调用 str.replaceAll（regex，repl ）}形式的此方法 与表达式完全相同的结果
     *
     *
     * <blockquote>
     * <code>
     * {@link java.util.regex.Pattern}.{@link
     * java.util.regex.Pattern#compile compile}(<i>regex</i>).{@link
     * java.util.regex.Pattern#matcher(java.lang.CharSequence) matcher}(<i>str</i>).{@link
     * java.util.regex.Matcher#replaceAll replaceAll}(<i>repl</i>)
     * </code>
     * </blockquote>
     *
     *<p>
     * Note that backslashes ({@code \}) and dollar signs ({@code $}) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string; see
     * {@link java.util.regex.Matcher#replaceAll Matcher.replaceAll}.
     * Use {@link java.util.regex.Matcher#quoteReplacement} to suppress the special
     * meaning of these characters, if desired.
     *
     * 请注意，替换字符串中的反斜杠（\）和美元符号（$）可能会导致结果与将其视为文字替换字符串时的结果不同;
     * 请参阅 java.util.regex.Matcher＃replaceAll Matcher.replaceAll 。
     * 如果需要，使用 java.util.regex.Matcher＃quoteReplacement 来抑制这些字符的特殊含义。
     *
     * @param   regex
     *          the regular expression to which this string is to be matched
     *          要与此字符串匹配的正则表达式
     *
     * @param   replacement
     *          the string to be substituted for each match
     *          要替换每个匹配的字符串
     *
     * @return  The resulting {@code String}
     *          结果 String
     *
     *
     * @throws  PatternSyntaxException  模式语法异常
     *          if the regular expression's syntax is invalid
     *          如果正则表达式的语法无效
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }

    /**
     * Replaces each substring of this string that matches the literal target
     * sequence with the specified literal replacement sequence. The
     * replacement proceeds from the beginning of the string to the end, for
     * example, replacing "aa" with "b" in the string "aaa" will result in
     * "ba" rather than "ab".
     *
     * 将与该文字目标序列匹配的此字符串的每个子字符串替换为指定的文字替换序列。
     * 替换从字符串的开头到结尾，例如，在字符串“aaa”中将“aa”替换为“b”将导致“ba”而不是“ab”。
     *
     *
     * @param  target The sequence of char values to be replaced
     *                要替换的char值序列
     *
     * @param  replacement The replacement sequence of char values
     *                     char值的替换序列
     *
     * @return  The resulting string
     *          结果字符串
     *
     * @since 1.5
     */
    public String replace(CharSequence target, CharSequence replacement) {
        String tgtStr = target.toString();
        String replStr = replacement.toString();
        int j = indexOf(tgtStr);
        if (j < 0) {
            return this;
        }
        int tgtLen = tgtStr.length();
        int tgtLen1 = Math.max(tgtLen, 1);
        int thisLen = length();

        int newLenHint = thisLen - tgtLen + replStr.length();
        if (newLenHint < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(newLenHint);
        int i = 0;
        do {
            sb.append(this, i, j).append(replStr);
            i = j + tgtLen;
        } while (j < thisLen && (j = indexOf(tgtStr, j + tgtLen1)) > 0);
        return sb.append(this, i, thisLen).toString();
    }

    /**
     * Splits this string around matches of the given
     * <a href="../util/regex/Pattern.html#sum">regular expression</a>.
     * 将此字符串拆分为给定正则表达式的匹配项
     *
     *
     * <p> The array returned by this method contains each substring of this
     * string that is terminated by another substring that matches the given
     * expression or is terminated by the end of the string.  The substrings in
     * the array are in the order in which they occur in this string.  If the
     * expression does not match any part of the input then the resulting array
     * has just one element, namely this string.
     *
     * 此方法返回的数组包含此字符串的每个子字符串，该子字符串由与给定表达式匹配的另一个子字符串终止，
     * 或者由字符串的结尾终止。 数组中的子串按它们在此字符串中出现的顺序排列。
     * 如果表达式与输入的任何部分都不匹配，那么结果数组只有一个元素，即该字符串。
     *
     *
     * <p> When there is a positive-width match at the beginning of this
     * string then an empty leading substring is included at the beginning
     * of the resulting array. A zero-width match at the beginning however
     * never produces such empty leading substring.
     *
     * 当在该字符串的开头存在正宽度匹配时，在结果数组的开头包含空的前导子字符串。
     * 然而，开头的零宽度匹配从不会产生这样的空前导子串。
     *
     * <p> The {@code limit} parameter controls the number of times the
     * pattern is applied and therefore affects the length of the resulting
     * array.
     *
     * limit 参数控制模式的应用次数，因此会影响结果数组的长度。
     *
     * <ul>
     *    <li><p>
     *    If the <i>limit</i> is positive then the pattern will be applied
     *    at most <i>limit</i>&nbsp;-&nbsp;1 times, the array's length will be
     *    no greater than <i>limit</i>, and the array's last entry will contain
     *    all input beyond the last matched delimiter.</p></li>
     *
     *    如果限制为正，那么该模式将最多应用 limit - 1次，
     *    数组的长度不会超过 limit ，数组的最后一个条目将包含除最后一个匹配分隔符之外的所有输入。
     *
     *
     *    <li><p>
     *    If the <i>limit</i> is zero then the pattern will be applied as
     *    many times as possible, the array can have any length, and trailing
     *    empty strings will be discarded.</p></li>
     *
     *    如果 limit 为零，那么模式将被应用尽可能多的次数，
     *    该数组可以具有任何长度，并且将丢弃尾随的空字符串。
     *
     *    <li><p>
     *    If the <i>limit</i> is negative then the pattern will be applied
     *    as many times as possible and the array can have any length.</p></li>
     *
     *    如果 limit 为负，则模式将被应用尽可能多次，并且数组可以具有任何长度。
     *
     * </ul>
     *
     * <p> The string {@code "boo:and:foo"}, for example, yields the
     * following results with these parameters:
     *
     * 例如，字符串 “boo：and：foo” 使用以下参数产生以下结果：
     *
     *
     * <blockquote><table class="plain">
     * <caption style="display:none">Split example showing regex, limit, and result</caption>
     *                              拆分示例显示正则表达式，限制和结果
     * <thead>
     * <tr>
     *     <th scope="col">Regex</th>
     *     <th scope="col">Limit</th>
     *     <th scope="col">Result</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr><th scope="row" rowspan="3" style="font-weight:normal">:</th>
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">2</th>
     *     <td>{@code { "boo", "and:foo" }}</td></tr>
     * <tr><!-- : -->
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">5</th>
     *     <td>{@code { "boo", "and", "foo" }}</td></tr>
     * <tr><!-- : -->
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">-2</th>
     *     <td>{@code { "boo", "and", "foo" }}</td></tr>
     * <tr><th scope="row" rowspan="3" style="font-weight:normal">o</th>
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">5</th>
     *     <td>{@code { "b", "", ":and:f", "", "" }}</td></tr>
     * <tr><!-- o -->
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">-2</th>
     *     <td>{@code { "b", "", ":and:f", "", "" }}</td></tr>
     * <tr><!-- o -->
     *     <th scope="row" style="font-weight:normal; text-align:right; padding-right:1em">0</th>
     *     <td>{@code { "b", "", ":and:f" }}</td></tr>
     * </tbody>
     * </table></blockquote>
     *
     * <p> An invocation of this method of the form
     * <i>str.</i>{@code split(}<i>regex</i>{@code ,}&nbsp;<i>n</i>{@code )}
     * yields the same result as the expression
     *
     * 调用此方法的形式 str。 split（ regex ， n ） 产生与表达式相同的结果
     *
     * <blockquote>
     * <code>
     * {@link java.util.regex.Pattern}.{@link
     * java.util.regex.Pattern#compile compile}(<i>regex</i>).{@link
     * java.util.regex.Pattern#split(java.lang.CharSequence,int) split}(<i>str</i>,&nbsp;<i>n</i>)
     * </code>
     * </blockquote>
     *
     *
     * @param  regex
     *         the delimiting regular expression
     *         分界正则表达式
     *
     * @param  limit
     *         the result threshold, as described above
     *         结果阈值，如上所述
     *
     * @return  the array of strings computed by splitting this string
     *          around matches of the given regular expression
     *          通过将此字符串拆分为给定正则表达式的匹配来计算的字符串数组
     *
     * @throws  PatternSyntaxException
     *          if the regular expression's syntax is invalid
     *          如果正则表达式的语法无效
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String[] split(String regex, int limit) {
        /* fastpath if the regex is a
         (1)one-char String and this character is not one of the
            RegEx's meta characters ".$|()[{^?*+\\", or
         (2)two-char String and the first char is the backslash and
            the second is not the ascii digit or ascii letter.
         */
        char ch = 0;
        if (((regex.length() == 1 &&
             ".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) ||
             (regex.length() == 2 &&
              regex.charAt(0) == '\\' &&
              (((ch = regex.charAt(1))-'0')|('9'-ch)) < 0 &&
              ((ch-'a')|('z'-ch)) < 0 &&
              ((ch-'A')|('Z'-ch)) < 0)) &&
            (ch < Character.MIN_HIGH_SURROGATE ||
             ch > Character.MAX_LOW_SURROGATE))
        {
            int off = 0;
            int next = 0;
            boolean limited = limit > 0;
            ArrayList<String> list = new ArrayList<>();
            while ((next = indexOf(ch, off)) != -1) {
                if (!limited || list.size() < limit - 1) {
                    list.add(substring(off, next));
                    off = next + 1;
                } else {    // last one
                    //assert (list.size() == limit - 1);
                    int last = length();
                    list.add(substring(off, last));
                    off = last;
                    break;
                }
            }
            // If no match was found, return this
            if (off == 0)
                return new String[]{this};

            // Add remaining segment
            if (!limited || list.size() < limit)
                list.add(substring(off, length()));

            // Construct result
            int resultSize = list.size();
            if (limit == 0) {
                while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
                    resultSize--;
                }
            }
            String[] result = new String[resultSize];
            return list.subList(0, resultSize).toArray(result);
        }
        return Pattern.compile(regex).split(this, limit);
    }

    /**
     * Splits this string around matches of the given <a
     * href="../util/regex/Pattern.html#sum">regular expression</a>.
     * 将此字符串拆分为给定正则表达式的匹配项
     *
     *
     * <p> This method works as if by invoking the two-argument {@link
     * #split(String, int) split} method with the given expression and a limit
     * argument of zero.  Trailing empty strings are therefore not included in
     * the resulting array.
     *
     * 此方法就像通过使用给定表达式和limit参数为零调用双参数 split（String，int）split 方法一样工作。
     * 因此，结尾的空字符串不包含在结果数组中。
     *
     * <p> The string {@code "boo:and:foo"}, for example, yields the following
     * results with these expressions:
     *
     * 例如，字符串 “boo：and：foo” 会产生以下结果：
     *
     * <blockquote><table class="plain">
     * <caption style="display:none">Split examples showing regex and result</caption>
     * <thead>
     * <tr>
     *  <th scope="col">Regex</th>
     *  <th scope="col">Result</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr><th scope="row" style="text-weight:normal">:</th>
     *     <td>{@code { "boo", "and", "foo" }}</td></tr>
     * <tr><th scope="row" style="text-weight:normal">o</th>
     *     <td>{@code { "b", "", ":and:f" }}</td></tr>
     * </tbody>
     * </table></blockquote>
     *
     *
     * @param  regex
     *         the delimiting regular expression
     *         分界正则表达式
     *
     * @return  the array of strings computed by splitting this string
     *          around matches of the given regular expression
     *          通过将此字符串拆分为给定正则表达式的匹配来计算的字符串数组
     *
     *
     * @throws  PatternSyntaxException
     *          if the regular expression's syntax is invalid
     *          如果正则表达式的语法无效
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String[] split(String regex) {
        return split(regex, 0);
    }

    /**
     * Returns a new String composed of copies of the
     * {@code CharSequence elements} joined together with a copy of
     * the specified {@code delimiter}.
     *
     * 返回一个新的 String，该字符串由 CharSequence elements 的副本组成，
     * 并与指定的 delimiter 的副本连接在一起。
     *
     * <blockquote>For example,
     * <pre>{@code
     *     String message = String.join("-", "Java", "is", "cool");
     *     // message returned is: "Java-is-cool"
     * }</pre></blockquote>
     *
     * Note that if an element is null, then {@code "null"} is added.
     * 请注意，如果元素为null，则添加 “null” 。
     *
     * @param  delimiter the delimiter that separates each element  分隔每个元素的分隔符
     * @param  elements the elements to join together. 加入的元素。
     *
     * @return a new {@code String} that is composed of the {@code elements}
     *         separated by the {@code delimiter}
     *
     *         一个新的 String ，由 delimiter 分隔的 elements 组成
     *
     * @throws NullPointerException If {@code delimiter} or {@code elements}
     *         is {@code null}
     *         如果 delimiter 或 elements 是 null
     *
     * @see java.util.StringJoiner
     * @since 1.8
     */
    public static String join(CharSequence delimiter, CharSequence... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrays.stream overhead.
        // 不太值得Arrays.stream开销的元素数量。
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }

    /**
     * Returns a new {@code String} composed of copies of the
     * {@code CharSequence elements} joined together with a copy of the
     * specified {@code delimiter}.
     *
     * 返回一个新的 String ，它由 CharSequence elements 的副本组成，
     * 并与指定的 delimiter 副本连接在一起。
     *
     * <blockquote>For example,
     * <pre>{@code
     *     List<String> strings = List.of("Java", "is", "cool");
     *     String message = String.join(" ", strings);
     *     //message returned is: "Java is cool"
     *
     *     Set<String> strings =
     *         new LinkedHashSet<>(List.of("Java", "is", "very", "cool"));
     *     String message = String.join("-", strings);
     *     //message returned is: "Java-is-very-cool"
     * }</pre></blockquote>
     *
     * Note that if an individual element is {@code null}, then {@code "null"} is added.
     * 请注意，如果单个元素是 null ，则添加“null”。
     *
     * @param  delimiter a sequence of characters that is used to separate each
     *         of the {@code elements} in the resulting {@code String}
     *                   一系列字符，用于分隔生成的 String 中的每个 String
     *
     * @param  elements an {@code Iterable} that will have its {@code elements}
     *         joined together.
     *                   Iterable 将 elements 连接在一起。
     *
     * @return a new {@code String} that is composed from the {@code elements}
     *         argument
     *
     *         一个由 elements 参数组成的新 String
     *
     * @throws NullPointerException If {@code delimiter} or {@code elements}
     *         is {@code null}
     *
     *         如果 delimiter 或 elements 是 null
     *
     * @see    #join(CharSequence,CharSequence...)
     * @see    java.util.StringJoiner
     * @since 1.8
     */
    public static String join(CharSequence delimiter,
            Iterable<? extends CharSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }

    /**
     * Converts all of the characters in this {@code String} to lower
     * case using the rules of the given {@code Locale}.  Case mapping is based
     * on the Unicode Standard version specified by the {@link java.lang.Character Character}
     * class. Since case mappings are not always 1:1 char mappings, the resulting
     * {@code String} may be a different length than the original {@code String}.
     *
     * 使用给定 Locale 的规则将此 String 中的所有字符转换为小写。
     * 编码映射基于 java.lang.Character Character 类指定的 Unicode 标准版本。
     * 由于大小写映射并不总是1：1的 char 映射，因此生成的 String 可能与原始 String 的长度不同。
     *
     * <p>
     * Examples of lowercase  mappings are in the following table:
     * 小写映射的示例如下表所示：
     *
     * <table class="plain">
     * <caption style="display:none">Lowercase mapping examples showing language code of locale, upper case, lower case, and description</caption>
     * <thead>
     * <tr>
     *   <th scope="col">Language Code of Locale</th>
     *   <th scope="col">Upper Case</th>
     *   <th scope="col">Lower Case</th>
     *   <th scope="col">Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">&#92;u0130</th>
     *   <td>&#92;u0069</td>
     *   <td>capital letter I with dot above -&gt; small letter i</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">&#92;u0049</th>
     *   <td>&#92;u0131</td>
     *   <td>capital letter I -&gt; small letter dotless i </td>
     * </tr>
     * <tr>
     *   <td>(all)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">French Fries</th>
     *   <td>french fries</td>
     *   <td>lowercased all chars in String</td>
     * </tr>
     * <tr>
     *   <td>(all)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">
     *       &Iota;&Chi;&Theta;&Upsilon;&Sigma;</th>
     *   <td>&iota;&chi;&theta;&upsilon;&sigma;</td>
     *   <td>lowercased all chars in String</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param locale use the case transformation rules for this locale
     *               使用此区域设置的大小写转换规则
     *
     * @return the {@code String}, converted to lowercase.
     *           返回 String ，转换为小写。
     *
     * @see     java.lang.String#toLowerCase()
     * @see     java.lang.String#toUpperCase()
     * @see     java.lang.String#toUpperCase(Locale)
     * @since   1.1
     */
    public String toLowerCase(Locale locale) {
        return isLatin1() ? StringLatin1.toLowerCase(this, value, locale)
                          : StringUTF16.toLowerCase(this, value, locale);
    }

    /**
     * Converts all of the characters in this {@code String} to lower
     * case using the rules of the default locale. This is equivalent to calling
     * {@code toLowerCase(Locale.getDefault())}.
     *
     * 使用默认语言环境的规则将此 String 中的所有字符转换为小写。
     * 这相当于调用 toLowerCase（Locale.getDefault（）） 。
     *
     * <p>
     * <b>Note:</b> This method is locale sensitive, and may produce unexpected
     * results if used for strings that are intended to be interpreted locale
     * independently.
     *
     * 注意：此方法对语言环境敏感，如果用于要独立解释语言环境的字符串，则可能会产生意外结果。
     *
     * Examples are programming language identifiers, protocol keys, and HTML
     * tags.
     * 示例是编程语言标识符，协议密钥和HTML标记。
     *
     * For instance, {@code "TITLE".toLowerCase()} in a Turkish locale
     * returns {@code "t\u005Cu0131tle"}, where '\u005Cu0131' is the
     * LATIN SMALL LETTER DOTLESS I character.
     * To obtain correct results for locale insensitive strings, use
     * {@code toLowerCase(Locale.ROOT)}.
     *
     * @return  the {@code String}, converted to lowercase.
     *          String ，转换为小写。
     *
     * @see     java.lang.String#toLowerCase(Locale)
     */
    public String toLowerCase() {
        return toLowerCase(Locale.getDefault());
    }

    /**
     * Converts all of the characters in this {@code String} to upper
     * case using the rules of the given {@code Locale}. Case mapping is based
     * on the Unicode Standard version specified by the {@link java.lang.Character Character}
     * class. Since case mappings are not always 1:1 char mappings, the resulting
     * {@code String} may be a different length than the original {@code String}.
     *
     * 使用给定 Locale 的规则将此 String 中的所有字符转换为大写。
     * 案例映射基于 java.lang.Character Character 类指定的Unicode标准版本。
     * 由于大小写映射并不总是1：1的char映射，因此生成的 String 可能与原始 String 的长度不同。
     *
     * <p>
     * Examples of locale-sensitive and 1:M case mappings are in the following table.
     *
     * 区域设置敏感和1：M 案例映射的示例如下表所示。
     *
     * <table class="plain">
     * <caption style="display:none">Examples of locale-sensitive and 1:M case mappings. Shows Language code of locale, lower case, upper case, and description.</caption>
     * <thead>
     * <tr>
     *   <th scope="col">Language Code of Locale</th>
     *   <th scope="col">Lower Case</th>
     *   <th scope="col">Upper Case</th>
     *   <th scope="col">Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">&#92;u0069</th>
     *   <td>&#92;u0130</td>
     *   <td>small letter i -&gt; capital letter I with dot above</td>
     * </tr>
     * <tr>
     *   <td>tr (Turkish)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">&#92;u0131</th>
     *   <td>&#92;u0049</td>
     *   <td>small letter dotless i -&gt; capital letter I</td>
     * </tr>
     * <tr>
     *   <td>(all)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">&#92;u00df</th>
     *   <td>&#92;u0053 &#92;u0053</td>
     *   <td>small letter sharp s -&gt; two letters: SS</td>
     * </tr>
     * <tr>
     *   <td>(all)</td>
     *   <th scope="row" style="font-weight:normal; text-align:left">Fahrvergn&uuml;gen</th>
     *   <td>FAHRVERGN&Uuml;GEN</td>
     *   <td></td>
     * </tr>
     * </tbody>
     * </table>
     * @param locale use the case transformation rules for this locale
     *               使用此语言环境的大小写转换规则
     *
     * @return the {@code String}, converted to uppercase.
     *          返回 String ，转换为大写。
     *
     * @see     java.lang.String#toUpperCase()
     * @see     java.lang.String#toLowerCase()
     * @see     java.lang.String#toLowerCase(Locale)
     * @since   1.1
     */
    public String toUpperCase(Locale locale) {
        return isLatin1() ? StringLatin1.toUpperCase(this, value, locale)
                          : StringUTF16.toUpperCase(this, value, locale);
    }

    /**
     * Converts all of the characters in this {@code String} to upper
     * case using the rules of the default locale. This method is equivalent to
     * {@code toUpperCase(Locale.getDefault())}.
     *
     * 使用默认语言环境的规则将此 String 中的所有字符转换为大写。
     * 此方法等同于 toUpperCase（Locale.getDefault（））
     *
     * <p>
     * <b>Note:</b> This method is locale sensitive, and may produce unexpected
     * results if used for strings that are intended to be interpreted locale
     * independently.
     *
     * 注意：此方法对语言环境敏感，如果用于要独立解释语言环境的字符串，则可能会产生意外结果。
     *
     * Examples are programming language identifiers, protocol keys, and HTML
     * tags.
     * 示例是编程语言标识符，协议密钥和HTML标记
     *
     * For instance, {@code "title".toUpperCase()} in a Turkish locale
     * returns {@code "T\u005Cu0130TLE"}, where '\u005Cu0130' is the
     * LATIN CAPITAL LETTER I WITH DOT ABOVE character.
     * To obtain correct results for locale insensitive strings, use
     * {@code toUpperCase(Locale.ROOT)}.
     *
     * 例如，土耳其语语言环境中的 “title”.toUpperCase（） 返回 “T \ u005Cu0130TLE” ，
     * 其中'\ u005Cu0130'是带有DOT ABOVE字符的LATIN CAPITAL LETTER I。
     * 要获取区域设置不敏感字符串的正确结果，请使用 toUpperCase（Locale.ROOT）。
     *
     * @return  the {@code String}, converted to uppercase.
     *           返回 String ，转换为大写。
     *
     * @see     java.lang.String#toUpperCase(Locale)
     */
    public String toUpperCase() {
        return toUpperCase(Locale.getDefault());
    }

    /**
     * Returns a string whose value is this string, with all leading
     * and trailing space removed, where space is defined
     * as any character whose codepoint is less than or equal to
     * {@code 'U+0020'} (the space character).
     *
     * 返回一个字符串，其值为此字符串，删除了所有前导和尾随空格，
     * 其中space被定义为其代码点小于或等于{@code'U + 0020'}（空格字符）的任何字符。
     *
     * <p>
     * If this {@code String} object represents an empty character
     * sequence, or the first and last characters of character sequence
     * represented by this {@code String} object both have codes
     * that are not space (as defined above), then a
     * reference to this {@code String} object is returned.
     *
     * 如果此 String 对象表示空字符序列，
     * 或者此 String 对象表示的字符序列的第一个和最后一个字符都具有非空格的代码（如上所述），
     * 则对此引用 返回 String 对象。
     *
     * <p>
     * Otherwise, if all characters in this string are space (as
     * defined above), then a  {@code String} object representing an
     * empty string is returned.
     *
     * 否则，如果此字符串中的所有字符都是空格（如上所述），则返回表示空字符串的 String 对象。
     *
     * <p>
     * Otherwise, let <i>k</i> be the index of the first character in the
     * string whose code is not a space (as defined above) and let
     * <i>m</i> be the index of the last character in the string whose code
     * is not a space (as defined above). A {@code String}
     * object is returned, representing the substring of this string that
     * begins with the character at index <i>k</i> and ends with the
     * character at index <i>m</i>-that is, the result of
     * {@code this.substring(k, m + 1)}.
     *
     * 否则，让 k  成为字符串中第一个字符的索引，
     * 其代码不是空格（如上所定义），让 m 成为最后一个字符的索引。
     * 代码不是空格的字符串（如上所定义）。 返回一个 String 对象，表示该字符串的子字符串，
     * 该字符串以索引 k  处的字符开头，并以索引 m  处的字符结束 - 即，
     * this.substring（k，m + 1） 的结果。
     *
     * <p>
     * This method may be used to trim space (as defined above) from
     * the beginning and end of a string.
     *
     * 此方法可用于从字符串的开头和结尾修剪空间（如上所述）。
     *
     *
     * @return  a string whose value is this string, with all leading
     *          and trailing space removed, or this string if it
     *          has no leading or trailing space.
     *
     *          一个字符串，其值为此字符串，删除了所有前导和尾随空格，如果没有前导或尾随空格，则为此字符串。
     */
    public String trim() {
        String ret = isLatin1() ? StringLatin1.trim(value)
                                : StringUTF16.trim(value);
        return ret == null ? this : ret;
    }

    /**
     * Returns a string whose value is this string, with all leading
     * and trailing {@link Character#isWhitespace(int) white space}
     * removed.
     *
     * 返回一个字符串，其值为此字符串，并删除所有前导和尾随 Character＃isWhitespace（int）空格 。
     *
     * <p>
     * If this {@code String} object represents an empty string,
     * or if all code points in this string are
     * {@link Character#isWhitespace(int) white space}, then an empty string
     * is returned.
     *
     * 如果此 String 对象表示空字符串，或者此字符串中的所有代码点都是
     * Character＃isWhitespace（int）white space ，则返回空字符串。
     *
     * <p>
     * Otherwise, returns a substring of this string beginning with the first
     * code point that is not a {@link Character#isWhitespace(int) white space}
     * up to and including the last code point that is not a
     * {@link Character#isWhitespace(int) white space}.
     *
     * 否则，返回此字符串的子字符串，该字符串以不是{@link Character＃isWhitespace（int）white space}
     * 的第一个代码点开头，包括最后一个不是{@link Character＃isWhitespace（int）的代码点 ）白色空间}。
     *
     * <p>
     * This method may be used to strip
     * {@link Character#isWhitespace(int) white space} from
     * the beginning and end of a string.
     *
     * 此方法可用于从字符串的开头和结尾剥离 Character＃isWhitespace（int）white space 。
     *
     * @return  a string whose value is this string, with all leading
     *          and trailing white space removed
     *
     *          一个字符串，其值为此字符串，删除了所有前导和尾随空格
     *
     * @see Character#isWhitespace(int)
     *
     * @since 11
     */
    public String strip() {
        String ret = isLatin1() ? StringLatin1.strip(value)
                                : StringUTF16.strip(value);
        return ret == null ? this : ret;
    }

    /**
     * Returns a string whose value is this string, with all leading
     * {@link Character#isWhitespace(int) white space} removed.
     *
     * 返回一个字符串，其值为此字符串，并删除所有前导 Character＃isWhitespace（int）white space 。
     *
     * <p>
     * If this {@code String} object represents an empty string,
     * or if all code points in this string are
     * {@link Character#isWhitespace(int) white space}, then an empty string
     * is returned.
     *
     * 如果此 String 对象表示空字符串，或者此字符串中的所有代码点都是 Character＃isWhitespace（int）white space ，
     * 则返回空字符串。
     *
     * <p>
     * Otherwise, returns a substring of this string beginning with the first
     * code point that is not a {@link Character#isWhitespace(int) white space}
     * up to to and including the last code point of this string.
     *
     * 否则，返回此字符串的子字符串，以不是 Character＃isWhitespace（int）white space 的第一个代码点开头，
     * 直到并包括该字符串的最后一个代码点。
     *
     * <p>
     * This method may be used to trim
     * {@link Character#isWhitespace(int) white space} from
     * the beginning of a string.
     *
     * 此方法可用于从字符串的开头修剪 Character＃isWhitespace（int）white space 。
     *
     *
     * @return  a string whose value is this string, with all leading white
     *          space removed
     *          一个字符串，其值为此字符串，删除了所有前导空格
     *
     * @see Character#isWhitespace(int)
     *
     * @since 11
     */
    public String stripLeading() {
        String ret = isLatin1() ? StringLatin1.stripLeading(value)
                                : StringUTF16.stripLeading(value);
        return ret == null ? this : ret;
    }

    /**
     * Returns a string whose value is this string, with all trailing
     * {@link Character#isWhitespace(int) white space} removed.
     *
     * 返回一个字符串，其值为此字符串，并删除所有尾随的 Character＃isWhitespace（int）white space 。
     *
     * <p>
     * If this {@code String} object represents an empty string,
     * or if all characters in this string are
     * {@link Character#isWhitespace(int) white space}, then an empty string
     * is returned.
     *
     * 如果此 String 对象表示空字符串，或者此字符串中的所有字符都是 Character＃isWhitespace（int）white space ，
     * 则返回空字符串。
     *
     * <p>
     * Otherwise, returns a substring of this string beginning with the first
     * code point of this string up to and including the last code point
     * that is not a {@link Character#isWhitespace(int) white space}.
     *
     * 否则，返回此字符串的子字符串，以该字符串的第一个代码点开头，
     * 包括最后一个不是 Character＃isWhitespace（int）white space 的代码点。
     *
     * <p>
     * This method may be used to trim
     * {@link Character#isWhitespace(int) white space} from
     * the end of a string.
     *
     * 此方法可用于从字符串末尾修剪{@link Character＃isWhitespace（int）white space}。
     *
     *
     * @return  a string whose value is this string, with all trailing white
     *          space removed
     *          一个字符串，其值为此字符串，并删除所有尾随空格
     *
     * @see Character#isWhitespace(int)
     *
     * @since 11
     */
    public String stripTrailing() {
        String ret = isLatin1() ? StringLatin1.stripTrailing(value)
                                : StringUTF16.stripTrailing(value);
        return ret == null ? this : ret;
    }

    /**
     * Returns {@code true} if the string is empty or contains only
     * {@link Character#isWhitespace(int) white space} codepoints,
     * otherwise {@code false}.
     *
     * 如果字符串为空或仅包含 Character＃isWhitespace（int）white space 代码点，
     * 则返回 true ，否则返回 false 。
     *
     * @return {@code true} if the string is empty or contains only
     *         {@link Character#isWhitespace(int) white space} codepoints,
     *         otherwise {@code false}
     *         返回 true 如果字符串为空或仅包含 Character＃isWhitespace（int）white space 代码点，否则 false
     *
     *
     * @see Character#isWhitespace(int)
     *
     * @since 11
     */
    public boolean isBlank() {
        return indexOfNonWhitespace() == length();
    }

    private int indexOfNonWhitespace() {
        if (isLatin1()) {
            return StringLatin1.indexOfNonWhitespace(value);
        } else {
            return StringUTF16.indexOfNonWhitespace(value);
        }
    }

    /**
     * Returns a stream of lines extracted from this string,
     * separated by line terminators.
     * 返回从此字符串中提取的行的流，由行终止符分隔。
     *
     * <p>
     * A <i>line terminator</i> is one of the following:
     * a line feed character {@code "\n"} (U+000A),
     * a carriage return character {@code "\r"} (U+000D),
     * or a carriage return followed immediately by a line feed
     * {@code "\r\n"} (U+000D U+000A).
     *
     * 行终止符是以下之一：换行符 “\ n” （U + 000A），回车符 “\ r” （U + 000D） ），
     * 或者回车后紧跟换行符 “\ r \ n” （U + 000D U + 000A）。
     *
     * <p>
     * A <i>line</i> is either a sequence of zero or more characters
     * followed by a line terminator, or it is a sequence of one or
     * more characters followed by the end of the string. A
     * line does not include the line terminator.
     *
     * 行 是零个或多个字符的序列，后跟行终止符，或者是一个或多个字符的序列，
     * 后跟字符串的结尾。 一行不包括行终止符。
     *
     * <p>
     * The stream returned by this method contains the lines from
     * this string in the order in which they occur.
     *
     * 此方法返回的流包含此字符串中出现的行的行。
     *
     *
     * @apiNote This definition of <i>line</i> implies that an empty
     *          string has zero lines and that there is no empty line
     *          following a line terminator at the end of a string.
     *
     *          line 的这个定义意味着空字符串具有零行，并且字符串末尾的行终止符后面没有空行。
     *
     *
     * @implNote This method provides better performance than
     *           split("\R") by supplying elements lazily and
     *           by faster search of new line terminators.
     *
     *           通过懒惰地提供元素并更快地搜索新的行终止符，此方法提供了比split（“\ R”）更好的性能。
     *
     * @return  the stream of lines extracted from this string
     *          从该字符串中提取的行流
     *
     * @since 11
     */
    public Stream<String> lines() {
        return isLatin1() ? StringLatin1.lines(value)
                          : StringUTF16.lines(value);
    }

    /**
     * This object (which is already a string!) is itself returned.
     * 这个对象（已经是一个字符串！）本身就返回了。
     *
     * @return  the string itself.
     *          字符串本身。
     */
    public String toString() {
        return this;
    }

    /**
     * Returns a stream of {@code int} zero-extending the {@code char} values
     * from this sequence.  Any char which maps to a <a
     * href="{@docRoot}/java.base/java/lang/Character.html#unicode">surrogate code
     * point</a> is passed through uninterpreted.
     *
     * 返回 int 的流，对此序列中的 char 值进行零扩展。
     * 映射到 代理代码点 的任何字符都将通过未解释的方式传递。
     *
     * @return an IntStream of char values from this sequence
     *          来自此序列的char值的IntStream
     * @since 9
     */
    @Override
    public IntStream chars() {
        return StreamSupport.intStream(
            isLatin1() ? new StringLatin1.CharsSpliterator(value, Spliterator.IMMUTABLE)
                       : new StringUTF16.CharsSpliterator(value, Spliterator.IMMUTABLE),
            false);
    }


    /**
     * Returns a stream of code point values from this sequence.  Any surrogate
     * pairs encountered in the sequence are combined as if by {@linkplain
     * Character#toCodePoint Character.toCodePoint} and the result is passed
     * to the stream. Any other code units, including ordinary BMP characters,
     * unpaired surrogates, and undefined code units, are zero-extended to
     * {@code int} values which are then passed to the stream.
     *
     * 返回此序列中的代码点值流。 序列中遇到的任何代理对都被组合，
     * 就像 Character＃toCodePoint Character.toCodePoint 一样，结果传递给流。
     * 任何其他代码单元（包括普通BMP字符，
     * 未配对代理和未定义代码单元）都将零扩展为 int 值，然后传递给流。
     *
     *
     * @return an IntStream of Unicode code points from this sequence
     *          Unicode 代码的 IntStream 指向此序列
     * @since 9
     */
    @Override
    public IntStream codePoints() {
        return StreamSupport.intStream(
            isLatin1() ? new StringLatin1.CharsSpliterator(value, Spliterator.IMMUTABLE)
                       : new StringUTF16.CodePointsSpliterator(value, Spliterator.IMMUTABLE),
            false);
    }

    /**
     * Converts this string to a new character array.
     * 将此字符串转换为新的字符数组。
     *
     * @return  a newly allocated character array whose length is the length
     *          of this string and whose contents are initialized to contain
     *          the character sequence represented by this string.
     *
     *          新分配的字符数组，其长度为此字符串的长度，其内容初始化为包含此字符串表示的字符序列。
     */
    public char[] toCharArray() {
        return isLatin1() ? StringLatin1.toChars(value)
                          : StringUTF16.toChars(value);
    }

    /**
     * Returns a formatted string using the specified format string and
     * arguments.
     *
     * 使用指定的格式字符串和参数返回格式化字符串。
     *
     * <p> The locale always used is the one returned by {@link
     * java.util.Locale#getDefault(java.util.Locale.Category)
     * Locale.getDefault(Locale.Category)} with
     * {@link java.util.Locale.Category#FORMAT FORMAT} category specified.
     *
     * 始终使用的语言环境是 java.util.Locale #getDefault（java.util.Locale.Category）
     * Locale.getDefault（Locale.Category） 返回的语言环境 java.util.Locale.Category #FORMAT 指定了FORMAT 类别。
     *
     *
     * @param  format
     *         A <a href="../util/Formatter.html#syntax">format string</a>
     *
     * @param  args
     *         Arguments referenced by the format specifiers in the format
     *         string.  If there are more arguments than format specifiers, the
     *         extra arguments are ignored.  The number of arguments is
     *         variable and may be zero.  The maximum number of arguments is
     *         limited by the maximum dimension of a Java array as defined by
     *         <cite>The Java&trade; Virtual Machine Specification</cite>.
     *         The behaviour on a
     *         {@code null} argument depends on the <a
     *         href="../util/Formatter.html#syntax">conversion</a>.
     *
     *         格式字符串中格式说明符引用的参数。 如果参数多于格式说明符，
     *         则忽略额外参数。 参数的数量是可变的，可以为零。
     *         参数的最大数量受到 Java＆trade 定义的 Java 数组的最大维数的限制; 虚拟机规范 。
     *         null 参数的行为取决于转换
     *
     * @throws  java.util.IllegalFormatException
     *          If a format string contains an illegal syntax, a format
     *          specifier that is incompatible with the given arguments,
     *          insufficient arguments given the format string, or other
     *          illegal conditions.  For specification of all possible
     *          formatting errors, see the <a
     *          href="../util/Formatter.html#detail">Details</a> section of the
     *          formatter class specification.
     *
     *          如果格式字符串包含非法语法，则格式说明符与给定参数不兼容，
     *          给定格式字符串的参数不足或其他非法条件。
     *          有关所有可能的格式错误的说明，请参阅详情格式化程序类规范的一部分。
     *
     * @return  A formatted string
     *
     * @see  java.util.Formatter
     * @since  1.5
     */
    public static String format(String format, Object... args) {
        return new Formatter().format(format, args).toString();
    }

    /**
     * Returns a formatted string using the specified locale, format string,
     * and arguments.
     * 使用指定的语言环境，格式字符串和参数返回格式化的字符串。
     *
     * @param  l
     *         The {@linkplain java.util.Locale locale} to apply during
     *         formatting.  If {@code l} is {@code null} then no localization
     *         is applied.
     *         要在格式化期间应用的 java.util.Locale语言环境 。 如果 l 是 null ，则不会应用本地化。
     *
     *
     * @param  format
     *         A <a href="../util/Formatter.html#syntax">format string</a>
     *
     * @param  args
     *         Arguments referenced by the format specifiers in the format
     *         string.  If there are more arguments than format specifiers, the
     *         extra arguments are ignored.  The number of arguments is
     *         variable and may be zero.  The maximum number of arguments is
     *         limited by the maximum dimension of a Java array as defined by
     *         <cite>The Java&trade; Virtual Machine Specification</cite>.
     *         The behaviour on a
     *         {@code null} argument depends on the
     *         <a href="../util/Formatter.html#syntax">conversion</a>.
     *         格式字符串中格式说明符引用的参数。 如果参数多于格式说明符，
     *         则忽略额外参数。 参数的数量是可变的，可以为零。
     *         参数的最大数量受到 Java＆trade定义的Java数组的最大维数的限制; 虚拟机规范 。
     *         null 参数的行为取决于转换
     *
     * @throws  java.util.IllegalFormatException
     *          If a format string contains an illegal syntax, a format
     *          specifier that is incompatible with the given arguments,
     *          insufficient arguments given the format string, or other
     *          illegal conditions.  For specification of all possible
     *          formatting errors, see the <a
     *          href="../util/Formatter.html#detail">Details</a> section of the
     *          formatter class specification
     *          如果格式字符串包含非法语法，则格式说明符与给定参数不兼容，
     *          给定格式字符串的参数不足或其他非法条件。 有关所有可能的格式错误的说明，
     *          请参阅 formatter 类规范的“详细信息”部分
     *
     * @return  A formatted string
     *          格式化的字符串
     *
     * @see  java.util.Formatter
     * @since  1.5
     */
    public static String format(Locale l, String format, Object... args) {
        return new Formatter(l).format(format, args).toString();
    }

    /**
     * Returns the string representation of the {@code Object} argument.
     * 返回 Object 参数的字符串表示形式。
     *
     * @param   obj   an {@code Object}.
     * @return  if the argument is {@code null}, then a string equal to
     *          {@code "null"}; otherwise, the value of
     *          {@code obj.toString()} is returned.
     *
     *          如果参数是 null ，则字符串等于 “null” ; 否则，返回 obj.toString（） 的值。
     *
     * @see     java.lang.Object#toString()
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }

    /**
     * Returns the string representation of the {@code char} array
     * argument. The contents of the character array are copied; subsequent
     * modification of the character array does not affect the returned
     * string.
     * 返回 char 数组参数的字符串表示形式。 复制字符数组的内容; 后续修改字符数组不会影响返回的字符串。
     *
     * @param   data     the character array.
     *                   字符数组。
     * @return  a {@code String} that contains the characters of the
     *          character array.
     *          一个 String ，包含字符数组的字符。
     */
    public static String valueOf(char data[]) {
        return new String(data);
    }

    /**
     * Returns the string representation of a specific subarray of the
     * {@code char} array argument.
     * 返回 char 数组参数的特定子数组的字符串表示形式。
     *
     * <p>
     * The {@code offset} argument is the index of the first
     * character of the subarray. The {@code count} argument
     * specifies the length of the subarray. The contents of the subarray
     * are copied; subsequent modification of the character array does not
     * affect the returned string.
     *
     *  offset 参数是子数组的第一个字符的索引。
     *  count 参数指定子数组的长度。 子阵列的内容被复制;
     *  后续修改字符数组不会影响返回的字符串。
     *
     * @param   data     the character array.
     *                   字符数组。
     *
     * @param   offset   initial offset of the subarray.
     *                   子阵列的初始偏移量。
     *
     * @param   count    length of the subarray.
     *                   子阵列的长度。
     *
     * @return  a {@code String} that contains the characters of the
     *          specified subarray of the character array.
     *          一个 String ，包含字符数组的指定子数组的字符。
     *
     * @exception IndexOutOfBoundsException if {@code offset} is
     *          negative, or {@code count} is negative, or
     *          {@code offset+count} is larger than
     *          {@code data.length}.
     *
     *          如果 offset 为负数，或 count 为负数，
     *          或 offset + count 大于 data.length 。
     */
    public static String valueOf(char data[], int offset, int count) {
        return new String(data, offset, count);
    }

    /**
     * Equivalent to {@link #valueOf(char[], int, int)}.
     * 相当于 #valueOf（char []，int，int）
     *
     * @param   data     the character array.
     *                   字符数组。
     *
     * @param   offset   initial offset of the subarray.
     *                   子阵列的初始偏移量。
     *
     * @param   count    length of the subarray.
     *                   子阵列的长度。
     *
     * @return  a {@code String} that contains the characters of the
     *          specified subarray of the character array.
     *          一个 String ，包含字符数组的指定子数组的字符。
     *
     * @exception IndexOutOfBoundsException if {@code offset} is
     *          negative, or {@code count} is negative, or
     *          {@code offset+count} is larger than
     *          {@code data.length}.
     *          如果 offset 为负数，或 count 为负数，或 offset + count 大于 data.length 。
     *
     */
    public static String copyValueOf(char data[], int offset, int count) {
        return new String(data, offset, count);
    }

    /**
     * Equivalent to {@link #valueOf(char[])}.
     * 相当于 #valueOf（char []） 。
     *
     * @param   data   the character array.
     *                 字符数组。
     *
     * @return  a {@code String} that contains the characters of the
     *          character array.
     *          一个 String ，包含字符数组的字符。
     */
    public static String copyValueOf(char data[]) {
        return new String(data);
    }

    /**
     * Returns the string representation of the {@code boolean} argument.
     * 返回 boolean 参数的字符串表示形式。
     *
     * @param   b   a {@code boolean}.
     *              一个 boolean 参数。
     *
     * @return  if the argument is {@code true}, a string equal to
     *          {@code "true"} is returned; otherwise, a string equal to
     *          {@code "false"} is returned.
     *
     *          如果参数为 true ，则返回等于 “true” 的字符串; 否则，返回等于 “false” 的字符串。
     */
    public static String valueOf(boolean b) {
        return b ? "true" : "false";
    }

    /**
     * Returns the string representation of the {@code char}
     * argument.
     * 返回 char 参数的字符串表示形式。
     *
     * @param   c   a {@code char}.
     *              a char .
     *
     * @return  a string of length {@code 1} containing
     *          as its single character the argument {@code c}.
     *          一个长度为 1 的字符串，其中包含参数 c 作为其单个字符。
     */
    public static String valueOf(char c) {
        if (COMPACT_STRINGS && StringLatin1.canEncode(c)) {
            return new String(StringLatin1.toBytes(c), LATIN1);
        }
        return new String(StringUTF16.toBytes(c), UTF16);
    }

    /**
     * Returns the string representation of the {@code int} argument.
     * 返回 int 参数的字符串表示形式。
     *
     * <p>
     * The representation is exactly the one returned by the
     * {@code Integer.toString} method of one argument.
     * 表示正好是一个参数的 Integer.toString 方法返回的表示。
     *
     * @param   i   an {@code int}.
     *              一个 int 参数
     *
     * @return  a string representation of the {@code int} argument.
     *
     *           返回 int 参数的字符串表示形式。
     *
     * @see     java.lang.Integer#toString(int, int)
     */
    public static String valueOf(int i) {
        return Integer.toString(i);
    }

    /**
     * Returns the string representation of the {@code long} argument.
     * 返回 long 参数的字符串表示形式。
     * <p>
     * The representation is exactly the one returned by the
     * {@code Long.toString} method of one argument.
     * 该表示正是一个参数的 Long.toString 方法返回的表示。
     *
     *
     * @param   l   a {@code long}.
     * @return  a string representation of the {@code long} argument.
     *          long 参数的字符串表示形式。
     *
     * @see     java.lang.Long#toString(long)
     */
    public static String valueOf(long l) {
        return Long.toString(l);
    }

    /**
     * Returns the string representation of the {@code float} argument.
     * 返回 float 参数的字符串表示形式。
     *
     * <p>
     * The representation is exactly the one returned by the
     * {@code Float.toString} method of one argument.
     * 该表示正是一个参数的 Float.toString 方法返回的表示。
     *
     *
     * @param   f   a {@code float}.
     * @return  a string representation of the {@code float} argument.
     *          float 参数的字符串表示形式。
     *
     * @see     java.lang.Float#toString(float)
     */
    public static String valueOf(float f) {
        return Float.toString(f);
    }

    /**
     * Returns the string representation of the {@code double} argument.
     * 返回 double 参数的字符串表示形式。
     *
     * <p>
     * The representation is exactly the one returned by the
     * {@code Double.toString} method of one argument.
     * 该表示形式正是一个参数的 Double.toString 方法返回的表示形式。
     *
     *
     * @param   d   a {@code double}.
     * @return  a  string representation of the {@code double} argument.
     *          double 参数的字符串表示形式。
     *
     * @see     java.lang.Double#toString(double)
     */
    public static String valueOf(double d) {
        return Double.toString(d);
    }

    /**
     * Returns a canonical representation for the string object.
     * 返回字符串对象的规范表示。
     *
     * <p>
     * A pool of strings, initially empty, is maintained privately by the
     * class {@code String}.
     * 最初为空的字符串池由类 String 私有维护。
     *
     * <p>
     * When the intern method is invoked, if the pool already contains a
     * string equal to this {@code String} object as determined by
     * the {@link #equals(Object)} method, then the string from the pool is
     * returned. Otherwise, this {@code String} object is added to the
     * pool and a reference to this {@code String} object is returned.
     *
     * 调用 intern 方法时，如果池已经包含与 #equals（Object） 方法确定的 String 对象相等的字符串，
     * 则返回池中的字符串。 否则，将此 String 对象添加到池中，并返回对此 String 对象的引用。
     *
     * <p>
     * It follows that for any two strings {@code s} and {@code t},
     * {@code s.intern() == t.intern()} is {@code true}
     * if and only if {@code s.equals(t)} is {@code true}.
     * 因此，对于任何两个字符串 s 和 t , s.intern（）== t.intern（） 是 true 当且仅当 s.equals（t） 是 true 。
     *
     * <p>
     * All literal strings and string-valued constant expressions are
     * interned. String literals are defined in section 3.10.5 of the
     * <cite>The Java&trade; Language Specification</cite>.
     * 所有文字字符串和字符串值常量表达式都是实体。 字符串文字在 The Java＆trade中的 3.10.5节中定义; 语言规范 。
     *
     * @return  a string that has the same contents as this string, but is
     *          guaranteed to be from a pool of unique strings.
     *          与此字符串具有相同内容的字符串，但保证来自唯一字符串池。
     *
     * @jls 3.10.5 String Literals
     */
    public native String intern();

    /**
     * Returns a string whose value is the concatenation of this
     * string repeated {@code count} times.
     * 返回一个字符串，其值是此字符串重复 count 次的串联。
     *
     * <p>
     * If this string is empty or count is zero then the empty
     * string is returned.
     * 如果此字符串为空或count为零，则返回空字符串
     *
     * @param   count number of times to repeat
     *                要重复的次数
     *
     * @return  A string composed of this string repeated
     *          {@code count} times or the empty string if this
     *          string is empty or count is zero
     *          由此字符串组成的字符串重复 count 次，如果此字符串为空或count为零，则为空字符串
     *
     * @throws  IllegalArgumentException if the {@code count} is
     *          negative.
     *
     * @since 11
     */
    public String repeat(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        }
        if (count == 1) {
            return this;
        }
        final int len = value.length;
        if (len == 0 || count == 0) {
            return "";
        }
        if (len == 1) {
            final byte[] single = new byte[count];
            Arrays.fill(single, value[0]);
            return new String(single, coder);
        }
        if (Integer.MAX_VALUE / count < len) {
            throw new OutOfMemoryError("Repeating " + len + " bytes String " + count +
                    " times will produce a String exceeding maximum size.");
        }
        final int limit = len * count;
        final byte[] multiple = new byte[limit];
        System.arraycopy(value, 0, multiple, 0, len);
        int copied = len;
        for (; copied < limit - copied; copied <<= 1) {
            System.arraycopy(multiple, 0, multiple, copied, copied);
        }
        System.arraycopy(multiple, 0, multiple, copied, limit - copied);
        return new String(multiple, coder);
    }

    ////////////////////////////////////////////////////////////////

    /**
     * Copy character bytes from this string into dst starting at dstBegin.
     * This method doesn't perform any range checking.
     *
     * 将此字符串中的字符字节从dstBegin复制到dst。 此方法不执行任何范围检查。
     *
     * Invoker guarantees: dst is in UTF16 (inflate itself for asb), if two
     * coders are different, and dst is big enough (range check)
     *
     * 祈求者保证：dst是UTF16（为asb充气），如果两个编码器不同，dst足够大（范围检查）
     *
     * @param dstBegin  the char index, not offset of byte[]
     *                  char索引，不是byte []的偏移量
     *
     * @param coder     the coder of dst[]
     *                  dst []的编码器
     */
    void getBytes(byte dst[], int dstBegin, byte coder) {
        if (coder() == coder) {
            System.arraycopy(value, 0, dst, dstBegin << coder, value.length);
        } else {    // this.coder == LATIN && coder == UTF16
            StringLatin1.inflate(value, 0, dst, dstBegin, value.length);
        }
    }

    /*
     * Package private constructor. Trailing Void argument is there for
     * disambiguating it against other (public) constructors.
     *
     * 包私有构造函数。最后的 Trailing Void 参数用于消除与其他（公共）构造函数的歧义。
     *
     * Stores the char[] value into a byte[] that each byte represents
     * the8 low-order bits of the corresponding character, if the char[]
     * contains only latin1 character. Or a byte[] that stores all
     * characters in their byte sequences defined by the {@code StringUTF16}.
     *
     * 将char []值存储到byte []中，如果char [] 仅包含 latin1 字符，则每个字节表示相应字符的 8 个低位。
     * 或者是一个 byte [] ，它存储的是由 StringUTF16 定义的字节序列中的所有字符。
     *
     */
    String(char[] value, int off, int len, Void sig) {
        if (len == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
        if (COMPACT_STRINGS) {
            byte[] val = StringUTF16.compress(value, off, len);
            if (val != null) {
                this.value = val;
                this.coder = LATIN1;
                return;
            }
        }
        this.coder = UTF16;
        this.value = StringUTF16.toBytes(value, off, len);
    }

    /*
     * Package private constructor. Trailing Void argument is there for
     * disambiguating it against other (public) constructors.
     */
    String(AbstractStringBuilder asb, Void sig) {
        byte[] val = asb.getValue();
        int length = asb.length();
        if (asb.isLatin1()) {
            this.coder = LATIN1;
            this.value = Arrays.copyOfRange(val, 0, length);
        } else {
            if (COMPACT_STRINGS) {
                byte[] buf = StringUTF16.compress(val, 0, length);
                if (buf != null) {
                    this.coder = LATIN1;
                    this.value = buf;
                    return;
                }
            }
            this.coder = UTF16;
            this.value = Arrays.copyOfRange(val, 0, length << 1);
        }
    }

   /*
    * Package private constructor which shares value array for speed.
    */
    String(byte[] value, byte coder) {
        this.value = value;
        this.coder = coder;
    }

    byte coder() {
        return COMPACT_STRINGS ? coder : UTF16;
    }

    byte[] value() {
        return value;
    }

    private boolean isLatin1() {
        return COMPACT_STRINGS && coder == LATIN1;
    }

    @Native static final byte LATIN1 = 0;
    @Native static final byte UTF16  = 1;

    /*
     * StringIndexOutOfBoundsException  if {@code index} is
     * negative or greater than or equal to {@code length}.
     */
    static void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new StringIndexOutOfBoundsException("index " + index +
                                                      ",length " + length);
        }
    }

    /*
     * StringIndexOutOfBoundsException  if {@code offset}
     * is negative or greater than {@code length}.
     */
    static void checkOffset(int offset, int length) {
        if (offset < 0 || offset > length) {
            throw new StringIndexOutOfBoundsException("offset " + offset +
                                                      ",length " + length);
        }
    }

    /*
     * Check {@code offset}, {@code count} against {@code 0} and {@code length}
     * bounds.
     *
     * 检查 offset , count 对 0 和 length 的界限。
     *
     * @throws  StringIndexOutOfBoundsException    索引超出界限异常
     *          If {@code offset} is negative, {@code count} is negative,
     *          or {@code offset} is greater than {@code length - count}
     *
     *          如果 offset 为负数，count 为负数，或  offset 大于 value.length  -  count
     */
    static void checkBoundsOffCount(int offset, int count, int length) {
        if (offset < 0 || count < 0 || offset > length - count) {
            throw new StringIndexOutOfBoundsException(
                "offset " + offset + ", count " + count + ", length " + length);
        }
    }

    /*
     * Check {@code begin}, {@code end} against {@code 0} and {@code length}
     * bounds.
     *
     * @throws  StringIndexOutOfBoundsException
     *          If {@code begin} is negative, {@code begin} is greater than
     *          {@code end}, or {@code end} is greater than {@code length}.
     */
    static void checkBoundsBeginEnd(int begin, int end, int length) {
        if (begin < 0 || begin > end || end > length) {
            throw new StringIndexOutOfBoundsException(
                "begin " + begin + ", end " + end + ", length " + length);
        }
    }

    /**
     * Returns the string representation of the {@code codePoint}
     * argument.
     *
     * @param   codePoint a {@code codePoint}.
     * @return  a string of length {@code 1} or {@code 2} containing
     *          as its single character the argument {@code codePoint}.
     * @throws IllegalArgumentException if the specified
     *          {@code codePoint} is not a {@linkplain Character#isValidCodePoint
     *          valid Unicode code point}.
     */
    static String valueOfCodePoint(int codePoint) {
        if (COMPACT_STRINGS && StringLatin1.canEncode(codePoint)) {
            return new String(StringLatin1.toBytes((char)codePoint), LATIN1);
        } else if (Character.isBmpCodePoint(codePoint)) {
            return new String(StringUTF16.toBytes((char)codePoint), UTF16);
        } else if (Character.isSupplementaryCodePoint(codePoint)) {
            return new String(StringUTF16.toBytesSupplementary(codePoint), UTF16);
        }

        throw new IllegalArgumentException(
            format("Not a valid Unicode code point: 0x%X", codePoint));
    }
}
