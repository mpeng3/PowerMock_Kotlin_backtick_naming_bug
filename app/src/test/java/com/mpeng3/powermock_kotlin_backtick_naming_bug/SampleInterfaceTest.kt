package com.mpeng3.powermock_kotlin_backtick_naming_bug

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class SampleInterfaceTest {
    private var mockInterface = Mockito.mock<SampleInterface>(SampleInterface::class.java)

    // TODO - Uncomment this test to see strange behavior
    //  this exact same test, when named with underscores (as it is below) passes,
    //  but fails when written like this
    /*
    @Test
    fun `test with backtick naming fails with IllegalStateException if invocation is not used`() {
        var consumer: Consumer<Observable<SampleEvent>>? = null

        Mockito.doAnswer { invocation: InvocationOnMock ->
            //            consumer = invocation.getArgument(0)
            Unit
        }.`when`(mockInterface).startDiscovery(any())

        val myConsumer = Mockito.mock(Consumer::class.java)

        mockInterface.startDiscovery(myConsumer as Consumer<Observable<SampleEvent>>)

        verify(myConsumer, never()).accept(any())

        consumer?.accept(Observable.just(SampleEvent("foo")))

        verify(myConsumer, never()).accept(any()) // consumer is null so we invert the check here
    }
    */

    @Test
    fun test_with_underscore_naming_passes_even_if_invocation_is_not_used() {
        var consumer: Consumer<Observable<SampleEvent>>? = null

        Mockito.doAnswer { invocation: InvocationOnMock ->
            //            consumer = invocation.getArgument(0)
            Unit
        }.`when`(mockInterface).startDiscovery(any())

        val myConsumer = Mockito.mock(Consumer::class.java)

        mockInterface.startDiscovery(myConsumer as Consumer<Observable<SampleEvent>>)

        verify(myConsumer, never()).accept(any())

        consumer?.accept(Observable.just(SampleEvent("foo")))

        verify(myConsumer, never()).accept(any()) // consumer is null so we invert the check here
    }

    @Test
    fun `consumer called when discovery occurs`() {
        var consumer: Consumer<Observable<SampleEvent>>? = null

        Mockito.doAnswer { invocation: InvocationOnMock ->
            //            consumer = invocation.getArgument(0)
            Unit
        }.`when`(mockInterface).startDiscovery(any())

        val myConsumer = Mockito.mock(Consumer::class.java)

        mockInterface.startDiscovery(myConsumer as Consumer<Observable<SampleEvent>>)

        verify(myConsumer, never()).accept(any())

        consumer?.accept(Observable.just(SampleEvent("foo")))

        verify(myConsumer).accept(any())
    }

    @Test
    fun full_test_always_works() {
        var consumer: Consumer<Observable<SampleEvent>>? = null

        Mockito.doAnswer { invocation: InvocationOnMock ->
            consumer = invocation.getArgument(0)
            Unit
        }.`when`(mockInterface).startDiscovery(any())

        val myConsumer = Mockito.mock(Consumer::class.java)

        mockInterface.startDiscovery(myConsumer as Consumer<Observable<SampleEvent>>)

        verify(myConsumer, never()).accept(any())

        consumer?.accept(Observable.just(SampleEvent("foo")))

        verify(myConsumer).accept(any())
    }

    // TODO - Uncomment this to demonstrate a different problem with methods containing quotation marks
    /*
    @Test
    fun `all tests fail to run if test method name contains "quotes"`() {
    }
    */
}
