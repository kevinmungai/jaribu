# How to Wire Up Pedestal and Component

## What is Pedestal?

According to [pedestal.io](http://pedestal.io/):

> Pedestal is a set of libraries that we use to build services and applications.It runs in the back end and can serve up whole HTML pages or handle API requests.

Pedestal is a web framework for [Clojure](https://clojure.org/).
It is very flexible, one is not tied down to a particular deployment environment, server or the kind of web application one can build.

At the core of Pedestal is the concept of the **interceptor**.

> An interceptor is a pair of unary functions. Each function is called with a context map and must return either a context map or a channel that will deliver a context map.
>
> Pedestal calls the `:enter` function on the way "in" to handling a request. It calls the `:leave` function on the way back "out".

The `:enter` functions of interceptors are called in stack-line manner *last in, first out* and when all the functions are done, the `:leave` functions are called in a queue-like manner *first in, first out*. 

The weird but beautiful thing about Pedestal is that the context map being passed to interceptors has the interceptors inside it.
This means that that it is possible to change how the interceptors themselves are called based on application code.
This means that it is possible to have dynamic dispatching.



## What is Component?

## motivation

Why wire up Pedestal and Component 

## requirements



1. [component.pedestal] (https://clojars.org/grzm/component.pedestal)
2. 

`[com.grzm/component.pedestal "0.1.7"]`
