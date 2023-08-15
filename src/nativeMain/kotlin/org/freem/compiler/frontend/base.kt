package org.freem.compiler.frontend

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import okio.Path.Companion.toPath
import okio.buffer
import org.freem.Process
import org.freem.tools.io.fs

@OptIn(ExperimentalSerializationApi::class)
object CompileConfig {

    val compileConfigName: String = "frconfig.json"



    private val parser = ArgParser(Process.exeNameWithoutExtension)
    private val frconfigJsonObject = Json.decodeFromBufferedSource<JsonObject>(fs.source(compileConfigName.toPath()).buffer())



    val projectDir by parser
        .option(
            type = ArgType.String,
            fullName = "dir",
            shortName = "d",
            description = "project path"
        ).default(
            frconfigJsonObject["projectDir"]
                ?.jsonPrimitive
                ?.contentOrNull
                ?: "./"
        )



    val outputDir by parser
        .option(
            type = ArgType.String,
            fullName = "output",
            shortName = "o",
            description = "output path"
        ).default(
            frconfigJsonObject["output"]
                ?.jsonPrimitive
                ?.contentOrNull
                ?: "${this.projectDir}/bin"
        )



    init {
        parser.parse(Process.args)

        //val files = fs.listRecursively(if (fs.metadataOrNull(targetDirPath)?.isDirectory == true) targetDirPath else throw FileNotFoundException(""))
    }

}


fun run() {
    //fs.read(pathname.toPath(), BufferedSource::readUtf8)


//    val targetPath = "C:\\Users\\phill\\Workspace\\Development\\freem-compiler\\src\\nativeTest\\resources\\experiment-projects\\test\\test.fr".toPath()
//    val reader = FileSystem.SYSTEM.source(targetPath).buffer().buffer
//
//    repeat(30) {
//        print(Char(reader.readByte().toInt()))
//    }

//    FileSystem.SYSTEM.openReadOnly("".toPath())

}




//class FrontEndAnalyzer(private val iterator: Iterator<Char>, val file: File? = null): TextMatcher(iterator) {
//    constructor(iterable: Iterable<Char>, file: File? = null): this(iterable.iterator(), file)
//    constructor(string: String, file: File? = null): this(string.iterator(), file)
//
//
//    val expctdMsg: (event: MatcherEvent<Char>) -> Unit = { event -> println("expected ${event.expected}") }
//
//    private val s = (Char::isWhitespace or ("//" and any.lazy and '\n') or ("/*" and any.lazy and "*/")).plus
//    private val sa = s.opt
//
//    private val identifier = (Char::isLetter or '_') and (Char::isLetterOrDigit or '_').star
//    private val packageName = (identifier and (sa and '.' and sa and identifier).star)
//
//
//
//    private val expression: Pattern<Char> = TODO()
//
//    private val scope =
//                '{'.on("fail", expctdMsg) and
//                sa and
//                { false } and
//                sa and
//                '}'.on("fail", expctdMsg)
//
//    private val parameterDefine = '(' and sa and expression and sa and (',' and sa and expression and sa).star and ','.opt and sa and ')'
//    private val functionDefine = "func" and s and identifier and sa and parameterDefine and (sa and ':' and sa and identifier).opt and sa and scope
//
//    override val rootPattern = sa and
//            "package" and s and packageName and s and
//            ("import" and s and packageName and ((sa and '.' and sa and '*') or (s and "as" and s and identifier)).opt and s).star
//
//}
//
//data class MatcherEvent<T>(val isSucceeded: Boolean, val index: Int, val expected: Pattern<Char>)
//
//abstract class Matcher<T>(input: Iterator<T>) {
//    private val iterator: Iterator<T> = input
//
//    constructor(input: Iterable<T>): this(input.iterator())
//    constructor(input: Collection<T>): this(input.iterator())
//    constructor(input: Sequence<T>): this(input.iterator())
//    constructor(input: Array<T>): this(input.iterator())
//
//
//
//    protected abstract val rootPattern: Pattern<T>
//
//    fun match(input: T): Boolean {
//        TODO()
//    }
//
//    protected val any = { _: T -> true }.toPattern()
//
//
//
//    protected fun ((T) -> Boolean).toPattern(): Pattern<T> = TODO()
//    protected fun T.toPattern(): Pattern<T> = TODO()
//
//
//
//    protected infix fun ((T) -> Boolean).or(condition: (T) -> Boolean): Or<T> = Or(this.toPattern(), condition.toPattern())
//    protected infix fun ((T) -> Boolean).or(pattern: Pattern<T>): Or<T> = Or(this.toPattern(), pattern)
//    protected infix fun ((T) -> Boolean).or(value: T): Or<T> = Or(this.toPattern(), value.toPattern())
//
//    protected infix fun Pattern<T>.or(condition: (T) -> Boolean): Or<T> = Or(this, condition.toPattern())
//    protected infix fun Pattern<T>.or(pattern: Pattern<T>): Or<T> = Or(this, pattern)
//    protected infix fun Pattern<T>.or(value: T): Or<T> = Or(this, value.toPattern())
//
//    protected infix fun T.or(condition: (T) -> Boolean): Or<T> = Or(this.toPattern(), condition.toPattern())
//    protected infix fun T.or(pattern: Pattern<T>): Or<T> = Or(this.toPattern(), pattern)
//    protected infix fun T.or(value: T): Or<T> = Or(this.toPattern(), value.toPattern())
//
//    private fun Or(pattern: Pattern<T>, input: Pattern<T>): Or<T> {
//        TODO()
//    }
//
//    protected infix fun ((T) -> Boolean).and(condition: (T) -> Boolean): And<T> = And(this.toPattern(), condition.toPattern())
//    protected infix fun ((T) -> Boolean).and(pattern: Pattern<T>): And<T> = And(this.toPattern(), pattern)
//    protected infix fun ((T) -> Boolean).and(value: T): And<T> = And(this.toPattern(), value.toPattern())
//
//    protected infix fun Pattern<T>.and(condition: (T) -> Boolean): And<T> = And(this, condition.toPattern())
//    protected infix fun Pattern<T>.and(pattern: Pattern<T>): And<T> = And(this, pattern)
//    protected infix fun Pattern<T>.and(value: T): And<T> = And(this, value.toPattern())
//
//    protected infix fun T.and(condition: (T) -> Boolean): And<T> = And(this.toPattern(), condition.toPattern())
//    protected infix fun T.and(pattern: Pattern<T>): And<T> = And(this.toPattern(), pattern)
//    protected infix fun T.and(value: T): And<T> = And(this.toPattern(), value.toPattern())
//
//    private fun And(pattern: Pattern<T>, input: Pattern<T>): And<T> {
//        TODO()
//    }
//
//    protected val ((T) -> Boolean).plus: Plus<T> get()  = Plus(this.toPattern())
//    protected val Pattern<T>.plus: Plus<T> get()        = Plus(this)
//    protected val T.plus: Plus<T> get()                 = Plus(this.toPattern())
//
//    private fun Plus(pattern: Pattern<T>): Plus<T> {
//        TODO()
//    }
//
//    protected val ((T) -> Boolean).star: Star<T> get()  = Star(this.toPattern())
//    protected val Pattern<T>.star: Star<T> get()        = Star(this)
//    protected val T.star: Star<T> get()                 = Star(this.toPattern())
//
//    private fun Star(pattern: Pattern<T>): Star<T> {
//        TODO()
//    }
//
//    protected val ((T) -> Boolean).opt: Opt<T> get()    = Opt(this.toPattern())
//    protected val Pattern<T>.opt: Opt<T> get()          = Opt(this)
//    protected val T.opt: Opt<T> get()                   = Opt(this.toPattern())
//
//    private fun Opt(pattern: Pattern<T>): Opt<T> {
//        TODO()
//    }
//
//    protected val ((T) -> Boolean).lazy: Lazy<T> get()  = Lazy(this.toPattern())
//    protected val Pattern<T>.lazy: Lazy<T> get()        = Lazy(this)
//    protected val T.lazy: Lazy<T> get()                 = Lazy(this.toPattern())
//
//    private fun Lazy(pattern: Pattern<T>): Lazy<T> {
//        TODO()
//    }
//
//    protected fun ((T) -> Boolean).quan(min: Int, max: Int): Quan<T> = Quan(this.toPattern(), min, max)
//    protected fun ((T) -> Boolean).quan(min: Int): Quan<T> = Quan(this.toPattern(), min, null)
//    protected fun Pattern<T>.quan(min: Int, max: Int): Quan<T> = Quan(this, min, max)
//    protected fun Pattern<T>.quan(min: Int): Quan<T> = Quan(this, min, null)
//    protected fun T.quan(min: Int, max: Int): Quan<T> = Quan(this.toPattern(), min, max)
//    protected fun T.quan(min: Int): Quan<T> = Quan(this.toPattern(), min, null)
//
//    private fun Quan(pattern: Pattern<T>, min: Int, max: Int?): Quan<T> {
//        TODO()
//    }
//
//    protected fun ((T) -> Boolean).on(event: String, block: (event: MatcherEvent<T>) -> Unit): Pattern<T> = On(this.toPattern(), event, block)
//    protected fun Pattern<T>.on(event: String, block: (event: MatcherEvent<T>) -> Unit): Pattern<T> = On(this, event, block)
//    protected fun T.on(event: String, block: (event: MatcherEvent<T>) -> Unit): Pattern<T> = On(this.toPattern(), event, block)
//
//    private fun On(pattern: Pattern<T>, event: String, block: (event: MatcherEvent<T>) -> Unit): Pattern<T> {
//        TODO()
//    }
//
//}
//
//abstract class TextMatcher(input: Iterator<Char>): Matcher<Char>(input) {
//    constructor(input: Iterable<Char>): this(input.iterator())
//    constructor(input: Collection<Char>): this(input.iterator())
//    constructor(input: Sequence<Char>): this(input.iterator())
//    constructor(input: Array<Char>): this(input.iterator())
//    constructor(input: String): this(input.iterator())
//
//    protected fun String.toPattern(): Pattern<Char> = TODO()
//
//    protected infix fun ((Char) -> Boolean).or(value: String): Or<Char> = Or(toPattern(), value.toPattern())
//    protected infix fun Pattern<Char>.or(value: String): Or<Char> = Or(this, value.toPattern())
//    protected infix fun Char.or(value: String): Or<Char> = Or(toPattern(), value.toPattern())
//    protected infix fun String.or(condition: (Char) -> Boolean): Or<Char> = Or(toPattern(), condition.toPattern())
//    protected infix fun String.or(pattern: Pattern<Char>): Or<Char> = Or(toPattern(), pattern)
//    protected infix fun String.or(value: Char): Or<Char> = Or(toPattern(), value.toPattern())
//    protected infix fun String.or(value: String): Or<Char> = Or(toPattern(), value.toPattern())
//
//    private fun Or(pattern: Pattern<Char>, input: Pattern<Char>): Or<Char> {
//        TODO()
//    }
//
//    protected infix fun ((Char) -> Boolean).and(value: String): And<Char> = And(this.toPattern(), value.toPattern())
//    protected infix fun Pattern<Char>.and(value: String): And<Char> = And(this, value.toPattern())
//    protected infix fun Char.and(value: String): And<Char> = And(this.toPattern(), value.toPattern())
//    protected infix fun String.and(condition: (Char) -> Boolean): And<Char> = And(this.toPattern(), condition.toPattern())
//    protected infix fun String.and(pattern: Pattern<Char>): And<Char> = And(this.toPattern(), pattern)
//    protected infix fun String.and(value: Char): And<Char> = And(this.toPattern(), value.toPattern())
//    protected infix fun String.and(value: String): And<Char> = And(this.toPattern(), value.toPattern())
//
//    private fun And(pattern: Pattern<Char>, input: Pattern<Char>): And<Char> {
//        TODO()
//    }
//
//    protected val String.plus: Plus<Char> get() = TODO()
//    protected val String.star: Star<Char> get() = TODO()
//    protected val String.opt: Opt<Char> get() = TODO()
//    protected val String.lazy: Lazy<Char> get() = TODO()
//
//    protected fun String.quan(min: Int, max: Int): Quan<Char> = TODO()
//    protected fun String.quan(max: Int): Quan<Char> = TODO()
//
//    protected fun String.on(event: String, block: (event: MatcherEvent<Char>) -> Unit): Pattern<Char> = TODO()
//}
//
//interface Pattern<T>
//
//interface Or<T>: Pattern<T>
//interface And<T>: Pattern<T>
//
//interface Plus<T>: Pattern<T>
//interface Star<T>: Pattern<T>
//interface Opt<T>: Pattern<T>
//interface Quan<T>: Pattern<T>
//interface Lazy<T>: Pattern<T>
//
