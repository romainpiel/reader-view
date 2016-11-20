package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.ui.ViewModel
import com.romainpiel.readerview.utils.L
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.select.NodeVisitor
import rx.Observable

class DocumentToViewModels: Observable.Transformer<Document?, List<ViewModel>> {
    override fun call(o: Observable<Document?>?): Observable<List<ViewModel>> {
        if (o == null) return Observable.just(emptyList())
        return o.map {
            val viewModels = mutableListOf<ViewModel>()
            it?.traverse(object: NodeVisitor {
                var h1Seen = false
                var lastHeadingLevel = 0
                var inBlockQuote = false

                override fun head(node: Node?, depth: Int) {
                    if (node !is Element) return

                    val nodeHeadingLevel = getHeadingLevel(node)

                    h1Seen = h1Seen || nodeHeadingLevel == 1
                    if (!h1Seen) return

                    if (!inBlockQuote) {
                        inBlockQuote = node.tagName() == "blockquote"
                    }

                    if (node.ownText().isEmpty()) return

//                    if (nodeHeadingLevel != 0) {
//                        L.d("h$nodeHeadingLevel depth: $depth text: ${node.text()}")
//                    }

                    val viewModel = when (node.tagName()) {
                        "p" -> {
                            if (inBlockQuote) {
                                BlockquoteViewModel(node.text())
                            } else {
                                ParagraphViewModel(node.text())
                            }
                        }
                        "li" -> BulletViewModel(node.text())
                        "h1", "h2", "h3", "h4", "h5", "h6" -> HeadingViewModel(nodeHeadingLevel, node.text())
                        else -> {
                            L.d("could not handle tag ${node.tagName()}")
                            null
                        }
                    }

                    if (viewModel != null) viewModels.add(viewModel)

                    if (nodeHeadingLevel > 0) {
                        lastHeadingLevel = nodeHeadingLevel
                    }
                }
                override fun tail(node: Node?, depth: Int) {
                    if (node !is Element) return

                    if (inBlockQuote && node.tagName() == "blockquote") {
                        inBlockQuote = false
                    }
                }
            })
            return@map viewModels
        }
    }

    fun getHeadingLevel(element: Element): Int {
        return when (element.tagName()) {
            "h1" -> 1
            "h2" -> 2
            "h3" -> 3
            "h4" -> 4
            "h5" -> 5
            "h6" -> 6
            else -> 0
        }
    }
}