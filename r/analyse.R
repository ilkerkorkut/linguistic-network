library(igraph)

edgesData<-read.csv("edges.csv",header=TRUE,check.names=FALSE,sep="|",row.names=NULL)

relation <- data.frame(c(edgesData$source),c(edgesData$target))

g<-graph.data.frame(relation,directed = FALSE)

print(graph.knn(g))
print(degree.distribution(g))