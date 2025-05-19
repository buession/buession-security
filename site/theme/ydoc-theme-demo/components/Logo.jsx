<a href={relePath(props.distPath, "index.html")} target="_self">
  {
    props.nav.logo ? <img className="logo" width="70" src={relePath(props.distPath, props.nav.logo)} /> : null
  }
</a>